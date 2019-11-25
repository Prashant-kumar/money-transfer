package com.revolut.moneytransfer.model;

import com.revolut.moneytransfer.enums.TransactionStatus;
import com.revolut.moneytransfer.enums.TransactionType;

import java.util.UUID;

public class TransactionEntry {
    private UUID uuid;

    private UUID from;

    private UUID to;

    private Long amount;

    private TransactionStatus status;

    private TransactionType type;

    public TransactionEntry(UUID uuid, UUID from, UUID to, Long amount, TransactionStatus status, TransactionType type) {
        this.uuid = uuid;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public static TransactionEntry getTransferTransactionEntry(UUID from, UUID to, Long amount,
                                                               TransactionStatus status, TransactionType type) {
        return new TransactionEntry(UUID.randomUUID(), from, to, amount, TransactionStatus.NEW, TransactionType.TRANSFER);
    }

    public static TransactionEntry getCreditTransactionEntry(UUID to, Long amount) {
        return new TransactionEntry(UUID.randomUUID(), null, to, amount, TransactionStatus.NEW, TransactionType.CREDIT);
    }
}
