package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class InvalidUuidException extends RuntimeException {
    public InvalidUuidException() {
        super(Errors.INVALID_UUID);
    }
}
