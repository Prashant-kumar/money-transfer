package com.revolut.moneytransfer.model;

import java.util.UUID;

public class Account {
    private UUID uuid;

    private String name;

    private long balance;

    public Account() {
    }

    public Account(UUID uuid, String name, long balance) {
        this.uuid = uuid;
        this.name = name;
        this.balance = balance;
    }

    public static Account ofZeroBalance(UUID uuid, String name) {
        return new Account(uuid, name, 0);
    }

    public static Account of(UUID uuid, String name, long balance) {
        return new Account(uuid, name, balance);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
