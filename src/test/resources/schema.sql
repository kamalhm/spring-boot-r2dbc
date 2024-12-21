CREATE TABLE IF NOT EXISTS member
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS balance
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY,
    member_id          BIGINT    NOT NULL,
    balance            integer NOT NULL,
    created_by         TEXT    NOT NULL,
    created_date       bigint  NOT NULL,
    last_modified_date bigint  NOT NULL,
    last_modified_by   TEXT    NOT NULL,
    updated_by         TEXT
);
