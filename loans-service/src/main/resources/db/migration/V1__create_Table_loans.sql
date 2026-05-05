CREATE TABLE loans (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    book_id     BIGINT NOT NULL,
    loan_date   TIMESTAMP NOT NULL,
    return_date TIMESTAMP
);