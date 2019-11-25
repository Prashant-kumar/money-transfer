package com.revolut.moneytransfer.controller;

import com.revolut.moneytransfer.exceptions.InvalidUuidException;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import com.revolut.moneytransfer.util.RequestValidator;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.UUID;

import static com.revolut.moneytransfer.Application.transactionService;

public class TransactionController {


    public static Route creditMoney = (Request request, Response response) -> {
        try {
            CreditTransactionRequest creditTransactionRequest = RequestValidator.getCreditTransactionRequest(request.body());
            return transactionService.credit(creditTransactionRequest, UUID.fromString(request.params("uuid").trim()));
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException();
        }
    };

    public static Route transferMoney = (Request request, Response response) -> {
        try {
            MoneyTransferRequest moneyTransferRequest = RequestValidator.getMoneyTransferRequest(request.body());
            return transactionService.transfer(moneyTransferRequest, UUID.fromString(request.params("uuid").trim()));
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException();
        }
    };
}
