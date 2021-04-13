CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS balance (
    id uuid default uuid_generate_v4(),
    member_id TEXT NOT NULL,
    balance integer NOT NULL,
    created_by TEXT NOT NULL,
    created_date bigint NOT NULL,
    last_modified_date bigint NOT NULL,
    last_modified_by TEXT NOT NULL,
    updated_by TEXT
);
