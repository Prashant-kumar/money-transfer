package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;

import java.util.UUID;


public interface AccountService {

    public Account get(UUID uuid);

    public void save(Account account);

}
