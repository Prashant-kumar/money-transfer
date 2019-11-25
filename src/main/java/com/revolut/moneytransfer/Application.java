package com.revolut.moneytransfer;

import com.revolut.moneytransfer.config.WebConfig;
import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.repository.AccountRepository;
import com.revolut.moneytransfer.repository.TransactionRepository;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransactionService;
import com.revolut.moneytransfer.service.impl.AccountServiceImpl;
import com.revolut.moneytransfer.service.impl.TransactionServiceImpl;
import com.revolut.moneytransfer.util.Path;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

import static spark.Spark.*;
public class Application {
    public static final AccountService accountService;
    public static final TransactionService transactionService;
    static {
        DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:moneytransfer", "sa", "");
        Flyway.configure().dataSource(dataSource).load().migrate();
        DSLContext jooq = DSL.using(dataSource, SQLDialect.H2);
        AccountRepository accountRepository = new AccountRepository(jooq);
        TransactionRepository transactionRepository = new TransactionRepository(jooq);
        accountService = new AccountServiceImpl(accountRepository);
        transactionService = new TransactionServiceImpl(accountRepository, transactionRepository);
    }
    public static void main(String[] args) {
        port(8080);

        WebConfig.setupRoutes();
        WebConfig.setupExceptionHandler();
    }
}


