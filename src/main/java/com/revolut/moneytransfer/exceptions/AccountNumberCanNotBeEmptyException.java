package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class AccountNumberCanNotBeEmptyException extends RuntimeException {
    public AccountNumberCanNotBeEmptyException() {
        super(Errors.ACCOUNT_NUMBER_IS_REQUIRED);
    }
}
