CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(15)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role     VARCHAR(15)  NOT NULL
);
