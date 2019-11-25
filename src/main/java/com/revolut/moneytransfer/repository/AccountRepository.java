package com.revolut.moneytransfer.repository;

import com.revolut.moneytransfer.exceptions.AccountNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.persistence.jooq.Tables;
import com.revolut.moneytransfer.persistence.jooq.tables.records.AccountRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountRepository {
    private final DSLContext jooq;

    public AccountRepository(final DSLContext jooq) {
        this.jooq = jooq;
    }

    public int save(final Account account) {
        return jooq.insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.UUID, account.getUuid())
                .set(Tables.ACCOUNT.NAME, account.getName())
                .set(Tables.ACCOUNT.BALANCE, account.getBalance())
                .execute();
    }

    public Optional<Account> findByUUID(final UUID uuid) {
        return jooq.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchOptional(r -> Account.of(r.getUuid(), r.getName(), r.getBalance()));
    }

    public static AccountRecord lockAccount(final DSLContext dslContext, final UUID uuid) {
        return dslContext.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .forUpdate()
                .fetchOptional()
                .orElseThrow(() -> new AccountNotFoundException());
    }

}
