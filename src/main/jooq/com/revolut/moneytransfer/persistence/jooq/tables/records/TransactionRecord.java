/*
 * This file is generated by jOOQ.
 */
package com.revolut.moneytransfer.persistence.jooq.tables.records;


import com.revolut.moneytransfer.enums.TransactionStatus;
import com.revolut.moneytransfer.enums.TransactionType;
import com.revolut.moneytransfer.persistence.jooq.tables.Transaction;

import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class TransactionRecord extends UpdatableRecordImpl<TransactionRecord> implements Record6<UUID, UUID, UUID, Long, TransactionStatus, TransactionType> {

    private static final long serialVersionUID = 1238902108;

    /**
     * Setter for <code>PUBLIC.TRANSACTION.UUID</code>.
     */
    public TransactionRecord setUuid(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.TRANSACTION.UUID</code>.
     */
    public UUID getUuid() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>PUBLIC.TRANSACTION.FROM_ACCOUNT_ID</code>.
     */
    public TransactionRecord setFromAccountId(UUID value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.TRANSACTION.FROM_ACCOUNT_ID</code>.
     */
    public UUID getFromAccountId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>PUBLIC.TRANSACTION.TO_ACCOUNT_ID</code>.
     */
    public TransactionRecord setToAccountId(UUID value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.TRANSACTION.TO_ACCOUNT_ID</code>.
     */
    public UUID getToAccountId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>PUBLIC.TRANSACTION.AMOUNT</code>.
     */
    public TransactionRecord setAmount(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.TRANSACTION.AMOUNT</code>.
     */
    public Long getAmount() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>PUBLIC.TRANSACTION.STATUS</code>.
     */
    public TransactionRecord setStatus(TransactionStatus value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.TRANSACTION.STATUS</code>.
     */
    public TransactionStatus getStatus() {
        return (TransactionStatus) get(4);
    }

    /**
     * Setter for <code>PUBLIC.TRANSACTION.TYPE</code>.
     */
    public TransactionRecord setType(TransactionType value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.TRANSACTION.TYPE</code>.
     */
    public TransactionType getType() {
        return (TransactionType) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<UUID, UUID, UUID, Long, TransactionStatus, TransactionType> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<UUID, UUID, UUID, Long, TransactionStatus, TransactionType> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Transaction.TRANSACTION.UUID;
    }

    @Override
    public Field<UUID> field2() {
        return Transaction.TRANSACTION.FROM_ACCOUNT_ID;
    }

    @Override
    public Field<UUID> field3() {
        return Transaction.TRANSACTION.TO_ACCOUNT_ID;
    }

    @Override
    public Field<Long> field4() {
        return Transaction.TRANSACTION.AMOUNT;
    }

    @Override
    public Field<TransactionStatus> field5() {
        return Transaction.TRANSACTION.STATUS;
    }

    @Override
    public Field<TransactionType> field6() {
        return Transaction.TRANSACTION.TYPE;
    }

    @Override
    public UUID component1() {
        return getUuid();
    }

    @Override
    public UUID component2() {
        return getFromAccountId();
    }

    @Override
    public UUID component3() {
        return getToAccountId();
    }

    @Override
    public Long component4() {
        return getAmount();
    }

    @Override
    public TransactionStatus component5() {
        return getStatus();
    }

    @Override
    public TransactionType component6() {
        return getType();
    }

    @Override
    public UUID value1() {
        return getUuid();
    }

    @Override
    public UUID value2() {
        return getFromAccountId();
    }

    @Override
    public UUID value3() {
        return getToAccountId();
    }

    @Override
    public Long value4() {
        return getAmount();
    }

    @Override
    public TransactionStatus value5() {
        return getStatus();
    }

    @Override
    public TransactionType value6() {
        return getType();
    }

    @Override
    public TransactionRecord value1(UUID value) {
        setUuid(value);
        return this;
    }

    @Override
    public TransactionRecord value2(UUID value) {
        setFromAccountId(value);
        return this;
    }

    @Override
    public TransactionRecord value3(UUID value) {
        setToAccountId(value);
        return this;
    }

    @Override
    public TransactionRecord value4(Long value) {
        setAmount(value);
        return this;
    }

    @Override
    public TransactionRecord value5(TransactionStatus value) {
        setStatus(value);
        return this;
    }

    @Override
    public TransactionRecord value6(TransactionType value) {
        setType(value);
        return this;
    }

    @Override
    public TransactionRecord values(UUID value1, UUID value2, UUID value3, Long value4, TransactionStatus value5, TransactionType value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TransactionRecord
     */
    public TransactionRecord() {
        super(Transaction.TRANSACTION);
    }

    /**
     * Create a detached, initialised TransactionRecord
     */
    public TransactionRecord(UUID uuid, UUID fromAccountId, UUID toAccountId, Long amount, TransactionStatus status, TransactionType type) {
        super(Transaction.TRANSACTION);

        set(0, uuid);
        set(1, fromAccountId);
        set(2, toAccountId);
        set(3, amount);
        set(4, status);
        set(5, type);
    }
}
