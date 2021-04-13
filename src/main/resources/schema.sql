CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS balance (
    id uuid default uuid_generate_v4(),
    member_id TEXT NOT NULL,
    balance integer NOT NULL
);
