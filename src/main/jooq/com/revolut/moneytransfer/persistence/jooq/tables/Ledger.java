/*
 * This file is generated by jOOQ.
 */
package com.revolut.moneytransfer.persistence.jooq.tables;


import com.revolut.moneytransfer.persistence.jooq.Indexes;
import com.revolut.moneytransfer.persistence.jooq.Keys;
import com.revolut.moneytransfer.persistence.jooq.Public;
import com.revolut.moneytransfer.persistence.jooq.tables.records.LedgerRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Ledger extends TableImpl<LedgerRecord> {

    private static final long serialVersionUID = -1169639088;

    /**
     * The reference instance of <code>PUBLIC.LEDGER</code>
     */
    public static final Ledger LEDGER = new Ledger();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LedgerRecord> getRecordType() {
        return LedgerRecord.class;
    }

    /**
     * The column <code>PUBLIC.LEDGER.UUID</code>.
     */
    public final TableField<LedgerRecord, UUID> UUID = createField(DSL.name("UUID"), org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.LEDGER.ACCOUNT_ID</code>.
     */
    public final TableField<LedgerRecord, UUID> ACCOUNT_ID = createField(DSL.name("ACCOUNT_ID"), org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.LEDGER.AMOUNT</code>.
     */
    public final TableField<LedgerRecord, Long> AMOUNT = createField(DSL.name("AMOUNT"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.LEDGER.TRANSACTION_ID</code>.
     */
    public final TableField<LedgerRecord, UUID> TRANSACTION_ID = createField(DSL.name("TRANSACTION_ID"), org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.LEDGER</code> table reference
     */
    public Ledger() {
        this(DSL.name("LEDGER"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.LEDGER</code> table reference
     */
    public Ledger(String alias) {
        this(DSL.name(alias), LEDGER);
    }

    /**
     * Create an aliased <code>PUBLIC.LEDGER</code> table reference
     */
    public Ledger(Name alias) {
        this(alias, LEDGER);
    }

    private Ledger(Name alias, Table<LedgerRecord> aliased) {
        this(alias, aliased, null);
    }

    private Ledger(Name alias, Table<LedgerRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Ledger(Table<O> child, ForeignKey<O, LedgerRecord> key) {
        super(child, key, LEDGER);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_LEDGER_ACCOUNT_ID_INDEX_8, Indexes.PRIMARY_KEY_8);
    }

    @Override
    public UniqueKey<LedgerRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_8;
    }

    @Override
    public List<UniqueKey<LedgerRecord>> getKeys() {
        return Arrays.<UniqueKey<LedgerRecord>>asList(Keys.CONSTRAINT_8);
    }

    @Override
    public List<ForeignKey<LedgerRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<LedgerRecord, ?>>asList(Keys.FK_LEDGER_ACCOUNT_ID);
    }

    public Account account() {
        return new Account(this, Keys.FK_LEDGER_ACCOUNT_ID);
    }

    @Override
    public Ledger as(String alias) {
        return new Ledger(DSL.name(alias), this);
    }

    @Override
    public Ledger as(Name alias) {
        return new Ledger(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Ledger rename(String name) {
        return new Ledger(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ledger rename(Name name) {
        return new Ledger(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, UUID, Long, UUID> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
