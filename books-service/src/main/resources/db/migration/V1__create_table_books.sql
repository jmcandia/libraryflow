CREATE TABLE books (
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(200) NOT NULL,
    summary    TEXT,
    isbn       VARCHAR(20)  NOT NULL UNIQUE,
    author_id  BIGINT       NOT NULL,
    available  BOOLEAN      NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);