CREATE TABLE IF NOT EXISTS products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(4096),
    price       DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (price >= 0),
    available   BOOLEAN        NOT NULL DEFAULT FALSE
);
