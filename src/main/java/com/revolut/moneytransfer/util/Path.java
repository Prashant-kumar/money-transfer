package com.revolut.moneytransfer.util;

public class Path {
    public static final String ACCOUNT = "/accounts";
    public static final String ACCOUNT_DETAIL = "/accounts/:uuid";
    public static final String ACCOUNT_CREDIT = "/accounts/:uuid/credit";
    public static final String TRANSFER_MONEY = "/accounts/:uuid/transfer";
}
