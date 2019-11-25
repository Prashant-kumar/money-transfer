package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class NameCanNotBeEmtyException extends RuntimeException {
    public NameCanNotBeEmtyException() {
        super(Errors.ACCOUNT_NAME_IS_REQUIRED);
    }
}
