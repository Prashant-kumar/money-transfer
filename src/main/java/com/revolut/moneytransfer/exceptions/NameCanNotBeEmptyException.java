package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class NameCanNotBeEmptyException extends RuntimeException {
    public NameCanNotBeEmptyException() {
        super(Errors.ACCOUNT_NAME_IS_REQUIRED);
    }
}
