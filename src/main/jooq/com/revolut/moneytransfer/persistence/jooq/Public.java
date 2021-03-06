/*
 * This file is generated by jOOQ.
 */
package com.revolut.moneytransfer.persistence.jooq;


import com.revolut.moneytransfer.persistence.jooq.tables.Account;
import com.revolut.moneytransfer.persistence.jooq.tables.Ledger;
import com.revolut.moneytransfer.persistence.jooq.tables.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 2030751207;

    /**
     * The reference instance of <code>PUBLIC</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>PUBLIC.ACCOUNT</code>.
     */
    public final Account ACCOUNT = com.revolut.moneytransfer.persistence.jooq.tables.Account.ACCOUNT;

    /**
     * The table <code>PUBLIC.LEDGER</code>.
     */
    public final Ledger LEDGER = com.revolut.moneytransfer.persistence.jooq.tables.Ledger.LEDGER;

    /**
     * The table <code>PUBLIC.TRANSACTION</code>.
     */
    public final Transaction TRANSACTION = com.revolut.moneytransfer.persistence.jooq.tables.Transaction.TRANSACTION;

    /**
     * No further instances allowed
     */
    private Public() {
        super("PUBLIC", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Account.ACCOUNT,
            Ledger.LEDGER,
            Transaction.TRANSACTION);
    }
}
