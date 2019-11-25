package com.revolut.moneytransfer.repository;

import com.google.inject.Inject;
import com.revolut.moneytransfer.exceptions.AccountNotFoundException;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.persistence.jooq.Tables;
import com.revolut.moneytransfer.persistence.jooq.tables.records.AccountRecord;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

public class AccountRepository {
    private final DSLContext jooq;

    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    @Inject
    public AccountRepository(final DSLContext jooq) {
        this.jooq = jooq;
    }

    public static AccountRecord lockAccount(final DSLContext dslContext, final UUID uuid) {
        /* Locking the account is essesntial so that we can make sure that no other transaction is updating the balance.
         * */
        logger.info(String.format("Locking account with UUID : ", uuid));
        return dslContext.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .forUpdate()
                .fetchOptional()
                .orElseThrow(() -> new AccountNotFoundException());
    }

    public int save(final Account account) {
        return jooq.insertInto(Tables.ACCOUNT)
                .set(Tables.ACCOUNT.UUID, account.getUuid())
                .set(Tables.ACCOUNT.NAME, account.getName())
                .set(Tables.ACCOUNT.BALANCE, account.getBalance())
                .execute();
    }

    public Optional<Account> findByUUID(final UUID uuid) {
        // Finds an AccountRecord and converts that into Account.
        return jooq.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.UUID.eq(uuid))
                .fetchOptional(r -> Account.of(r.getUuid(), r.getName(), r.getBalance()));
    }

}
