package com.revolut.moneytransfer.config;

import com.google.inject.AbstractModule;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.repository.TransactionRepository;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransactionService;
import com.revolut.moneytransfer.service.impl.AccountServiceImpl;
import com.revolut.moneytransfer.service.impl.TransactionServiceImpl;
import org.jooq.DSLContext;

import static com.revolut.moneytransfer.Application.jooq;

public class MoneyTransferModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransactionService.class).to(TransactionServiceImpl.class);
        bind(AccountService.class).to(AccountServiceImpl.class);

        bind(AccountRepository.class);
        bind(TransactionRepository.class);

        bind(DSLContext.class).toInstance(jooq);
    }
}
