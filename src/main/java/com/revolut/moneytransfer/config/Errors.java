package com.revolut.moneytransfer.config;

public class Errors {
    public static final String ACCOUNT_NAME_IS_REQUIRED = "Account name is mandatory.";
    public static final String ACCOUNT_NOT_FOUND = "Account not found.";
    public static final String INVALID_UUID = "Invalid UUID.";

    public static class ErrorResponse {
        private  String message;

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
