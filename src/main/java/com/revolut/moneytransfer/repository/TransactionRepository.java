package com.revolut.moneytransfer.repository;

import com.google.inject.Inject;
import com.revolut.moneytransfer.config.WebConfig;
import com.revolut.moneytransfer.enums.TransactionStatus;
import com.revolut.moneytransfer.exceptions.InsufficientBalanceException;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.LedgerEntry;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import com.revolut.moneytransfer.model.TransactionEntry;
import com.revolut.moneytransfer.persistence.jooq.Tables;
import com.revolut.moneytransfer.persistence.jooq.tables.records.AccountRecord;
import com.revolut.moneytransfer.persistence.jooq.tables.records.LedgerRecord;
import com.revolut.moneytransfer.persistence.jooq.tables.records.TransactionRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TransactionRepository {
    private final DSLContext jooq;

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    @Inject
    public TransactionRepository(final DSLContext jooq) {
        this.jooq = jooq;
    }

    private static void addTransactionEntry(DSLContext ctx, TransactionEntry transactionEntry) {
        logger.info(String.format("Adding transaction with uuid : %s", transactionEntry.getUuid()));
        ctx.insertInto(Tables.TRANSACTION)
                .set(Tables.TRANSACTION.UUID, transactionEntry.getUuid())
                .set(Tables.TRANSACTION.FROM_ACCOUNT_ID, transactionEntry.getFrom())
                .set(Tables.TRANSACTION.TO_ACCOUNT_ID, transactionEntry.getTo())
                .set(Tables.TRANSACTION.AMOUNT, transactionEntry.getAmount())
                .set(Tables.TRANSACTION.STATUS, transactionEntry.getStatus())
                .set(Tables.TRANSACTION.TYPE, transactionEntry.getType())
                .execute();
    }

    private static void addLedgerEntry(DSLContext ctx, LedgerEntry ledgerEntry) {
        logger.info(String.format("Adding Ledger entry with uuid : %s", ledgerEntry.getUuid()));
        ctx.insertInto(Tables.LEDGER)
                .set(Tables.LEDGER.UUID, ledgerEntry.getUuid())
                .set(Tables.LEDGER.ACCOUNT_ID, ledgerEntry.getAccountId())
                .set(Tables.LEDGER.AMOUNT, ledgerEntry.getAmount())
                .set(Tables.LEDGER.TRANSACTION_ID, ledgerEntry.getTransactionId())
                .execute();
    }

    public TransactionRecord[] getTransactionRecordByFromAccountId(final UUID uuid) {
        return jooq.selectFrom(Tables.TRANSACTION)
                .where(Tables.TRANSACTION.FROM_ACCOUNT_ID.eq(uuid))
                .fetchArray();
    }

    public TransactionRecord[] getTransactionRecordByToAccountId(final UUID uuid) {
        return jooq.selectFrom(Tables.TRANSACTION)
                .where(Tables.TRANSACTION.TO_ACCOUNT_ID.eq(uuid))
                .fetchArray();
    }

    public LedgerRecord[] getLedgerRecordByAccountId(final UUID uuid) {
        return jooq.selectFrom(Tables.LEDGER)
                .where(Tables.LEDGER.ACCOUNT_ID.eq(uuid))
                .fetchArray();
    }

    public void transferMoney(MoneyTransferRequest moneyTransferRequest, UUID fromUuid) {
        /* This method first creates objects of TransactionEntry and LedgerEntry classes, I am doing that outside of
        * transaction. Inside the transaction I take a lock on the source account and validate that balance > amount. If
        * not I mark the transaction FAILED otherwise I take the lock on the destination account as well, update the
        * balance of both and create the ledger statements. Ledger statements are important in case we have to reconcile.
        * For one transfer transaction I am creating two Ledger entries, one for source account with -amount and other
        * for destination account with +amount.
        *
        * The sum of all ledger entries for a particular account should be the balance for that account.
        * */
        TransactionEntry transactionEntry = TransactionEntry.getTransferTransactionEntry(
                fromUuid, moneyTransferRequest.getTo(), moneyTransferRequest.getAmount());
        LedgerEntry creditLedgerEntry = LedgerEntry.getCreditLedgerEntry(fromUuid, moneyTransferRequest.getTo(),
                moneyTransferRequest.getAmount(), transactionEntry.getUuid());

        LedgerEntry debitLedgerEntry = LedgerEntry.getDebitLedgerEntry(fromUuid, moneyTransferRequest.getTo(),
                moneyTransferRequest.getAmount(), transactionEntry.getUuid());
        jooq.transaction(c -> {
            DSLContext dslContext = DSL.using(c);
            AccountRecord fromAccountRecord = AccountRepository.lockAccount(dslContext, fromUuid);
            if (fromAccountRecord.getBalance() < moneyTransferRequest.getAmount()) {
                transactionEntry.setStatus(TransactionStatus.FAILED);
                addTransactionEntry(dslContext, transactionEntry);
            } else {
                AccountRecord toAccountRecord = AccountRepository.lockAccount(dslContext, moneyTransferRequest.getTo());
                addLedgerEntry(dslContext, creditLedgerEntry);
                addLedgerEntry(dslContext, debitLedgerEntry);
                transactionEntry.setStatus(TransactionStatus.COMPLETED);
                addTransactionEntry(dslContext, transactionEntry);
                fromAccountRecord.setBalance(fromAccountRecord.getBalance() - moneyTransferRequest.getAmount());
                fromAccountRecord.store();
                toAccountRecord.setBalance(toAccountRecord.getBalance() + moneyTransferRequest.getAmount());
                toAccountRecord.store();
            }
        });
        if (TransactionStatus.FAILED.equals(transactionEntry.getStatus())) {
            throw new InsufficientBalanceException();
        }
    }

    public void addCreditToAccount(CreditTransactionRequest creditTransactionRequest, UUID toUuid) {
        /*CREDIT transaction is a special case of money transfer where we don't have any source account,
        * hence I am creating only one ledger entry for destination account.
        * */
        TransactionEntry transactionEntry = TransactionEntry.getCreditTransactionEntry(
                toUuid, creditTransactionRequest.getAmount());
        LedgerEntry creditLedgerEntry = LedgerEntry.getCreditLedgerEntry(null, toUuid,
                creditTransactionRequest.getAmount(), transactionEntry.getUuid());
        jooq.transaction(c -> {
            DSLContext dslContext = DSL.using(c);
            AccountRecord accountRecord = AccountRepository.lockAccount(dslContext, toUuid);
            addLedgerEntry(dslContext, creditLedgerEntry);
            transactionEntry.setStatus(TransactionStatus.COMPLETED);
            addTransactionEntry(dslContext, transactionEntry);
            accountRecord.setBalance(accountRecord.getBalance() + creditTransactionRequest.getAmount());
            accountRecord.store();
        });
    }

}
