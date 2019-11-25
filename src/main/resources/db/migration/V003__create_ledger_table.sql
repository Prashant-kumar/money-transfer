CREATE TABLE ledger
(
    uuid UUID NOT NULL,
    account_id UUID NOT NULL,
    amount BIGINT NOT NULL,
    transaction_id UUID NOT NULL,

    PRIMARY KEY (uuid),
    CONSTRAINT fk_ledger_account_id FOREIGN KEY (account_id) REFERENCES account (uuid)
);
