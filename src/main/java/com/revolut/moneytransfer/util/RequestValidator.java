package com.revolut.moneytransfer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revolut.moneytransfer.exceptions.AccountNumberCanNotBeEmptyException;
import com.revolut.moneytransfer.exceptions.AmountIsNegativeException;
import com.revolut.moneytransfer.exceptions.NameCanNotBeEmptyException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.AccountCreateRequest;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import spark.utils.StringUtils;


import java.util.Objects;
import java.util.UUID;

public class RequestValidator {

    public static Account getNewAccount(String requestBody) throws JsonProcessingException {

        AccountCreateRequest request =
                (AccountCreateRequest)JsonHelper.convert(requestBody, AccountCreateRequest.class);
        if(StringUtils.isBlank(request.getName())) {
            throw new NameCanNotBeEmptyException();
        }
        return Account.ofZeroBalance(UUID.randomUUID(), request.getName());
    }

    public static CreditTransactionRequest getCreditTransactionRequest(String requestBody) throws JsonProcessingException {

        CreditTransactionRequest request =
                (CreditTransactionRequest)JsonHelper.convert(requestBody, CreditTransactionRequest.class);

        if(Objects.isNull(request.getAmount()) || request.getAmount() <= 0L) {
            throw new AmountIsNegativeException();
        }
        return request;
    }

    public static MoneyTransferRequest getMoneyTransferRequest(String requestBody) throws JsonProcessingException {

        MoneyTransferRequest request =
                (MoneyTransferRequest)JsonHelper.convert(requestBody, MoneyTransferRequest.class);

        if(Objects.isNull(request.getAmount()) || request.getAmount() <= 0L) {
            throw new AmountIsNegativeException();
        }
        if(Objects.isNull(request.getTo())) {
            throw new AccountNumberCanNotBeEmptyException();
        }
        return request;
    }
}
