CREATE TABLE transaction
(
    uuid UUID NOT NULL,
    from_account_id UUID,
    to_account_id UUID NOT NULL,
    amount BIGINT(20) NOT NULL,
    status ENUM('NEW', 'FAILED', 'COMPLETED') NOT NULL,
    type ENUM('CREDIT', 'TRANSFER') NOT NULL,
    PRIMARY KEY (uuid),
    CONSTRAINT fk_from_account_id FOREIGN KEY (from_account_id) REFERENCES account (uuid),
    CONSTRAINT fk_to_account_id FOREIGN KEY (to_account_id) REFERENCES account (uuid)
);