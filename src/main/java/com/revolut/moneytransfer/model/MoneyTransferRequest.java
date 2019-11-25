package com.revolut.moneytransfer.model;

import java.util.UUID;

public class MoneyTransferRequest {

    private UUID to;
    private Long amount;

    public MoneyTransferRequest() {
    }

    public MoneyTransferRequest(UUID to, Long amount) {
        this.to = to;
        this.amount = amount;
    }

    public static MoneyTransferRequest of(UUID to, Long amount) {
        return new MoneyTransferRequest(to, amount);
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
