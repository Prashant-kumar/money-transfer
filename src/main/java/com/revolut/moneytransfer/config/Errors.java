package com.revolut.moneytransfer.config;

public class Errors {
    // All the error messages I return via API.
    public static final String ACCOUNT_NAME_IS_REQUIRED = "Account name is mandatory.";
    public static final String ACCOUNT_NUMBER_IS_REQUIRED = "Account number is mandatory.";
    public static final String ACCOUNT_NOT_FOUND = "Account not found.";
    public static final String INVALID_UUID = "Invalid UUID.";
    public static final String AMOUNT_IS_NEGATIVE = "Amount must be a postive number greater than 0.";
    public static final String INSUFFICIENT_BALANCE = "Account balance is less than the amount.";

    // A simple Error response class I use to return errors.
    public static class ErrorResponse {
        private String message;

        public ErrorResponse() {
        }

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static ErrorResponse of(String message) {
            return new ErrorResponse(message);
        }
    }
}
