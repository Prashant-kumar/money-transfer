package com.revolut.moneytransfer.service.impl;

import com.revolut.moneytransfer.exceptions.AccountNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.repository.TransactionRepository;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransactionService;

import java.util.Optional;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account credit(CreditTransactionRequest creditTransactionRequest, UUID to) {
        transactionRepository.addCreditToAccount(creditTransactionRequest, to);
        return accountRepository.findByUUID(to).get();
    }

    @Override
    public Account transfer(MoneyTransferRequest moneyTransferRequest, UUID from) {
        transactionRepository.transferMoney(moneyTransferRequest, from);
        return accountRepository.findByUUID(from).get();
    }
}
