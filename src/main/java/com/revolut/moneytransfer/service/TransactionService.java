package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;

import java.util.UUID;


public interface TransactionService {

    public Account credit(CreditTransactionRequest creditTransactionRequest, UUID to);

    public Account transfer(MoneyTransferRequest moneyTransferRequest, UUID from);
}
