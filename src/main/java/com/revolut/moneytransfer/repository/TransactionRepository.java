package com.revolut.moneytransfer.repository;

import com.revolut.moneytransfer.exceptions.InsufficientBalanceException;
import com.revolut.moneytransfer.model.*;
import com.revolut.moneytransfer.persistence.jooq.Tables;
import com.revolut.moneytransfer.persistence.jooq.tables.records.AccountRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.UUID;

public class TransactionRepository {
    private final DSLContext jooq;

    public TransactionRepository(final DSLContext jooq) {
        this.jooq = jooq;
    }

    public void transferMoney(MoneyTransferRequest moneyTransferRequest, UUID fromUuid) {
        TransactionEntry transactionEntry = TransactionEntry.getTransferTransactionEntry(
                fromUuid, moneyTransferRequest.getTo(), moneyTransferRequest.getAmount());
        LedgerEntry creditLedgerEntry = LedgerEntry.getCreditLedgerEntry(fromUuid, moneyTransferRequest.getTo(),
                moneyTransferRequest.getAmount(), transactionEntry.getUuid());

        LedgerEntry debitLedgerEntry = LedgerEntry.getDebitLedgerEntry(fromUuid, moneyTransferRequest.getTo(),
                moneyTransferRequest.getAmount(), transactionEntry.getUuid());
        jooq.transaction(c -> {
            DSLContext dslContext = DSL.using(c);
            AccountRecord fromAccountRecord = AccountRepository.lockAccount(dslContext, fromUuid);
            if(fromAccountRecord.getBalance() < moneyTransferRequest.getAmount()) {
                throw new InsufficientBalanceException();
            }
            AccountRecord toAccountRecord = AccountRepository.lockAccount(dslContext, moneyTransferRequest.getTo());
            addLedgerEntry(dslContext, creditLedgerEntry);
            addLedgerEntry(dslContext, debitLedgerEntry);
            addTransactionEntry(dslContext, transactionEntry);
            fromAccountRecord.setBalance(fromAccountRecord.getBalance() - moneyTransferRequest.getAmount());
            fromAccountRecord.store();
            toAccountRecord.setBalance(toAccountRecord.getBalance() + moneyTransferRequest.getAmount());
            toAccountRecord.store();
        });
    }

    public void addCreditToAccount(CreditTransactionRequest creditTransactionRequest, UUID toUuid) {
        TransactionEntry transactionEntry = TransactionEntry.getCreditTransactionEntry(
                toUuid, creditTransactionRequest.getAmount());
        LedgerEntry creditLedgerEntry = LedgerEntry.getCreditLedgerEntry(null, toUuid,
                creditTransactionRequest.getAmount(), transactionEntry.getUuid());
        jooq.transaction(c -> {
            DSLContext dslContext = DSL.using(c);
            AccountRecord accountRecord = AccountRepository.lockAccount(dslContext, toUuid);
            addLedgerEntry(dslContext, creditLedgerEntry);
            addTransactionEntry(dslContext, transactionEntry);
            accountRecord.setBalance(accountRecord.getBalance() + creditTransactionRequest.getAmount());
            accountRecord.store();
        });
    }

    private static void addTransactionEntry(DSLContext ctx, TransactionEntry transactionEntry) {
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
        ctx.insertInto(Tables.LEDGER)
                .set(Tables.LEDGER.UUID, ledgerEntry.getUuid())
                .set(Tables.LEDGER.ACCOUNT_ID, ledgerEntry.getAccountId())
                .set(Tables.LEDGER.AMOUNT, ledgerEntry.getAmount())
                .set(Tables.LEDGER.TRANSACTION_ID, ledgerEntry.getTransactionId())
                .execute();
    }

}
