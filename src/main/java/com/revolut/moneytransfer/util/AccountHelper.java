package com.revolut.moneytransfer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revolut.moneytransfer.exceptions.NameCanNotBeEmtyException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.AccountCreateRequest;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.UUID;

public class AccountHelper {

    public static Account getNewAccount(String requestBody) throws JsonProcessingException {

        AccountCreateRequest request =
                (AccountCreateRequest)JsonHelper.convert(requestBody, AccountCreateRequest.class);
        if(Strings.isBlank(request.getName())) {
            throw new NameCanNotBeEmtyException();
        }
        return Account.ofZeroBalance(UUID.randomUUID(), request.getName());
    }
}
