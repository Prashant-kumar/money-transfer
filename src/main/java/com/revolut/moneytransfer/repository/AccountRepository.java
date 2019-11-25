package com.revolut.moneytransfer.repository;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.persistence.jooq.Tables;
import org.jooq.DSLContext;

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

    public Account getAny() {
        return jooq.selectFrom(Tables.ACCOUNT)
                .fetchAny(r -> Account.of(r.getUuid(), r.getName(), r.getBalance()));
    }
}
