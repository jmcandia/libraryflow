CREATE TABLE authors (
    id          BIGINT       PRIMARY KEY AUTO_INCREMENT,
    full_name   VARCHAR(100) NOT NULL,
    biography   TEXT,
    nationality VARCHAR(50)  NOT NULL,
    birth_date  DATE,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);