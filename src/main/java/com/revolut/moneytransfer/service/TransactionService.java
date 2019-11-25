package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import com.revolut.moneytransfer.persistence.jooq.tables.records.LedgerRecord;
import com.revolut.moneytransfer.persistence.jooq.tables.records.TransactionRecord;

import java.util.UUID;


public interface TransactionService {

    Account credit(CreditTransactionRequest creditTransactionRequest, UUID to);

    Account transfer(MoneyTransferRequest moneyTransferRequest, UUID from);

    TransactionRecord[] getTransactionRecordByFromAccountId(final UUID uuid);

    TransactionRecord[] getTransactionRecordByToAccountId(final UUID uuid);

    LedgerRecord[] getLedgerRecordByAccountId(final UUID uuid);
}
