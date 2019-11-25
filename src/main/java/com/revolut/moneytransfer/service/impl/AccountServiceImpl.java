package com.revolut.moneytransfer.service.impl;

import com.google.inject.Inject;
import com.revolut.moneytransfer.exceptions.AccountNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.service.AccountService;

import java.util.Optional;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Inject
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account get(UUID uuid) {
        Optional<Account> account = accountRepository.findByUUID(uuid);
        return account.orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
