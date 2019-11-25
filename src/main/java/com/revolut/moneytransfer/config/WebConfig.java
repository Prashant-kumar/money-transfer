package com.revolut.moneytransfer.config;

import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.controller.TransactionController;
import com.revolut.moneytransfer.exceptions.*;
import com.revolut.moneytransfer.util.JsonHelper;
import com.revolut.moneytransfer.util.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class WebConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    public static void setupRoutes() {
        logger.info("Adding all the routs with their handlers.");
        put(Path.ACCOUNT, AccountController.createAccount, JsonHelper::convert);
        get(Path.ACCOUNT_DETAIL, AccountController.getAccount, JsonHelper::convert);
        post(Path.ACCOUNT_CREDIT, TransactionController.creditMoney, JsonHelper::convert);
        post(Path.TRANSFER_MONEY, TransactionController.transferMoney, JsonHelper::convert);


        // All of our APIs return JSON.
        after((request, response) -> {
            response.header("content-type", "application/json");
        });
    }

    public static void setupExceptionHandler() {
        logger.info("Adding Exception mappers.");
        exception(AccountNotFoundException.class, (e, request, response) -> {
            response.status(404);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });

        exception(NameCanNotBeEmptyException.class, (e, request, response) -> {
            response.status(400);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });


        exception(InvalidUuidException.class, (e, request, response) -> {
            response.status(400);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });

        exception(AmountIsNegativeException.class, (e, request, response) -> {
            response.status(400);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });

        exception(InsufficientBalanceException.class, (e, request, response) -> {
            response.status(400);
            response.body(JsonHelper.convert(Errors.ErrorResponse.of(e.getMessage())));
            response.header("content-type", "application/json");
        });


    }

}
