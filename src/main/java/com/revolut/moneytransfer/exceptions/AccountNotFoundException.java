package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super(Errors.ACCOUNT_NOT_FOUND);
    }
}
