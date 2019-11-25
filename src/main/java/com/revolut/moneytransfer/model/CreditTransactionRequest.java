package com.revolut.moneytransfer.model;

public class CreditTransactionRequest {

    private Long amount;

    public CreditTransactionRequest() {
    }

    public CreditTransactionRequest(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public static CreditTransactionRequest of(Long amount) {
        return new CreditTransactionRequest(amount);
    }
}
