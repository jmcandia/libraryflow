CREATE TABLE users (
    id         BIGINT        PRIMARY KEY AUTO_INCREMENT,
    full_name  VARCHAR(100)  NOT NULL,
    email      VARCHAR(100)  NOT NULL UNIQUE,
    phone      VARCHAR(20),
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);