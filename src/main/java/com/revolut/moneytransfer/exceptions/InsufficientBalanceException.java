package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super(Errors.INSUFFICIENT_BALANCE);
    }
}
