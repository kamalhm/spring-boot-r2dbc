CREATE TABLE IF NOT EXISTS member (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS balance (
    id TEXT PRIMARY KEY,
    balance integer NOT NULL
);
