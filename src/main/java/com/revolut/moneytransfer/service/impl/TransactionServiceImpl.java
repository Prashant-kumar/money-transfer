package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.revolut.moneytransfer.exceptions.AccountNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import com.revolut.moneytransfer.persistence.jooq.tables.records.LedgerRecord;
import com.revolut.moneytransfer.persistence.jooq.tables.records.TransactionRecord;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.repository.TransactionRepository;
import com.revolut.moneytransfer.service.TransactionService;

import java.util.Optional;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Inject
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account credit(CreditTransactionRequest creditTransactionRequest, UUID to) {
        accountRepository.findByUUID(to).orElseThrow(AccountNotFoundException::new);
        transactionRepository.addCreditToAccount(creditTransactionRequest, to);
        return accountRepository.findByUUID(to).get();
    }

    @Override
    public Account transfer(MoneyTransferRequest moneyTransferRequest, UUID from) {
        accountRepository.findByUUID(from).orElseThrow(AccountNotFoundException::new);
        accountRepository.findByUUID(moneyTransferRequest.getTo()).orElseThrow(AccountNotFoundException::new);

        transactionRepository.transferMoney(moneyTransferRequest, from);
        return accountRepository.findByUUID(from).get();
    }

    @Override
    public TransactionRecord[] getTransactionRecordByFromAccountId(UUID uuid) {
        return transactionRepository.getTransactionRecordByFromAccountId(uuid);
    }

    @Override
    public TransactionRecord[] getTransactionRecordByToAccountId(UUID uuid) {
        return transactionRepository.getTransactionRecordByToAccountId(uuid);
    }

    @Override
    public LedgerRecord[] getLedgerRecordByAccountId(UUID uuid) {
        return transactionRepository.getLedgerRecordByAccountId(uuid);
    }
}
