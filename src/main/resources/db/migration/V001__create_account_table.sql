CREATE TABLE account
(
    uuid UUID NOT NULL,
    name VARCHAR(64) NOT NULL,
    balance BIGINT(20) NOT NULL,

    PRIMARY KEY (uuid)
);
