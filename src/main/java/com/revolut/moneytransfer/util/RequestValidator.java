package com.revolut.moneytransfer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revolut.moneytransfer.exceptions.AmountIsNegativeException;
import com.revolut.moneytransfer.exceptions.NameCanNotBeEmtyException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.AccountCreateRequest;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import spark.utils.StringUtils;


import java.util.Objects;
import java.util.UUID;

public class RequestValidator {

    public static Account getNewAccount(String requestBody) throws JsonProcessingException {

        AccountCreateRequest request =
                (AccountCreateRequest)JsonHelper.convert(requestBody, AccountCreateRequest.class);
        if(StringUtils.isBlank(request.getName())) {
            throw new NameCanNotBeEmtyException();
        }
        return Account.ofZeroBalance(UUID.randomUUID(), request.getName());
    }

    public static CreditTransactionRequest getCreditTransactionRequest(String requestBody) throws JsonProcessingException {

        CreditTransactionRequest request =
                (CreditTransactionRequest)JsonHelper.convert(requestBody, CreditTransactionRequest.class);

        if(Objects.isNull(request.getAmount()) || request.getAmount() <= 0L) {
            throw new AmountIsNegativeException();
        }
        return CreditTransactionRequest.of(request.getAmount());
    }
}
