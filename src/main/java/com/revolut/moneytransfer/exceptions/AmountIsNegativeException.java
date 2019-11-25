package com.revolut.moneytransfer.exceptions;

import com.revolut.moneytransfer.config.Errors;

public class AmountIsNegativeException extends RuntimeException {
    public AmountIsNegativeException() {
        super(Errors.AMOUNT_IS_NEGATIVE);
    }
}
