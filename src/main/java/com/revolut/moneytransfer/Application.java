package com.revolut.moneytransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.revolut.moneytransfer.config.MoneyTransferModule;
import com.revolut.moneytransfer.config.WebConfig;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransactionService;
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
    public static final DSLContext jooq;
    static {
        DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:moneytransfer", "sa", "");
        Flyway.configure().dataSource(dataSource).load().migrate();
        jooq = DSL.using(dataSource, SQLDialect.H2);

        Injector injector = Guice.createInjector(new MoneyTransferModule());
        accountService = injector.getInstance(AccountService.class);
        transactionService = injector.getInstance(TransactionService.class);
    }
    public static void startApplication(int port) {
        port(port);
        WebConfig.setupRoutes();
        WebConfig.setupExceptionHandler();
    }
    public static void main(String[] args) {
        startApplication(8080);
    }
}


