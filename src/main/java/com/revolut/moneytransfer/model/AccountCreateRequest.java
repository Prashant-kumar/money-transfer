package com.revolut.moneytransfer.model;

public class AccountCreateRequest {

    private String name;

    public AccountCreateRequest() {
    }

    public AccountCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
