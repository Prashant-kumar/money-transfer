package com.revolut.moneytransfer.model;

import java.util.UUID;

public class LedgerEntry {

    private UUID uuid;

    private UUID accountId;

    private Long amount;

    private UUID transactionId;

    public LedgerEntry(UUID uuid, UUID accountId, Long amount, UUID transactionId) {
        this.uuid = uuid;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    public static LedgerEntry getCreditLedgerEntry(UUID from, UUID to, Long amount, UUID transactionId) {
        return new LedgerEntry(UUID.randomUUID(), to, amount, transactionId);
    }

    public static LedgerEntry getDebitLedgerEntry(UUID from, UUID to, Long amount, UUID transactionId) {
        return new LedgerEntry(UUID.randomUUID(), from, amount * -1, transactionId);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
}
