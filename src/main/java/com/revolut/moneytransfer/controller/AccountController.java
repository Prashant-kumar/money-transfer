package com.revolut.moneytransfer.controller;

import com.revolut.moneytransfer.exceptions.InvalidUuidException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.util.AccountHelper;
import com.revolut.moneytransfer.util.JsonHelper;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.UUID;
import static com.revolut.moneytransfer.Application.accountService;
public class AccountController {


    public static Route createAccount = (Request request, Response response) -> {
        Account account= AccountHelper.getNewAccount(request.body());
        accountService.save(account);
        response.status(HttpStatus.CREATED_201);
        return account;
    };
    public static Route getAccount = (Request request, Response response) -> {
        try {
            return accountService.get(UUID.fromString(request.params("uuid").trim()));
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException();
        }
    };
}
