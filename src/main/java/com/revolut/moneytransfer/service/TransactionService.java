package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.CreditTransactionRequest;

import java.util.UUID;


public interface TransactionService {

    public Account credit(CreditTransactionRequest creditTransactionRequest, UUID to);
}
