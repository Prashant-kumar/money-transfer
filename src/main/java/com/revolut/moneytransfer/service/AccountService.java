package com.revolut.moneytransfer.service;

import com.revolut.moneytransfer.model.Account;

import java.util.UUID;


public interface AccountService {

    Account get(UUID uuid);

    void save(Account account);

}
