package com.revolut.moneytransfer.config;

import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.exceptions.AccountNotFoundException;
import com.revolut.moneytransfer.exceptions.InvalidUuidException;
import com.revolut.moneytransfer.exceptions.NameCanNotBeEmtyException;
import com.revolut.moneytransfer.util.JsonHelper;
import com.revolut.moneytransfer.util.Path;

import static spark.Spark.*;

public class WebConfig {
    public static void setupRoutes() {
        put(Path.ACCOUNT, AccountController.createAccount, JsonHelper::convert);
        get(Path.ACCOUNT_DETAIL, AccountController.getAccount, JsonHelper::convert);
        post(Path.TRANSACTION, AccountController.createAccount, JsonHelper::convert);


        after((request, response) -> {
            response.header("content-type", "application/json");
        });
    }

    public static void setupExceptionHandler() {
        exception(AccountNotFoundException.class, (e, request, response) -> {
            response.status(404);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });

        exception(NameCanNotBeEmtyException.class, (e, request, response) -> {
            response.status(400);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });


        exception(InvalidUuidException.class, (e, request, response) -> {
            response.status(400);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });


    }

}
