/*
 * This file is generated by jOOQ.
 */
package com.revolut.moneytransfer.persistence.jooq.tables.records;


import com.revolut.moneytransfer.persistence.jooq.tables.Account;

import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountRecord extends UpdatableRecordImpl<AccountRecord> implements Record3<UUID, String, Long> {

    private static final long serialVersionUID = 857685241;

    /**
     * Setter for <code>PUBLIC.ACCOUNT.UUID</code>.
     */
    public AccountRecord setUuid(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.UUID</code>.
     */
    public UUID getUuid() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.NAME</code>.
     */
    public AccountRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.BALANCE</code>.
     */
    public AccountRecord setBalance(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.BALANCE</code>.
     */
    public Long getBalance() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, String, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Account.ACCOUNT.UUID;
    }

    @Override
    public Field<String> field2() {
        return Account.ACCOUNT.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Account.ACCOUNT.BALANCE;
    }

    @Override
    public UUID component1() {
        return getUuid();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long component3() {
        return getBalance();
    }

    @Override
    public UUID value1() {
        return getUuid();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Long value3() {
        return getBalance();
    }

    @Override
    public AccountRecord value1(UUID value) {
        setUuid(value);
        return this;
    }

    @Override
    public AccountRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public AccountRecord value3(Long value) {
        setBalance(value);
        return this;
    }

    @Override
    public AccountRecord values(UUID value1, String value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountRecord
     */
    public AccountRecord() {
        super(Account.ACCOUNT);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(UUID uuid, String name, Long balance) {
        super(Account.ACCOUNT);

        set(0, uuid);
        set(1, name);
        set(2, balance);
    }
}
