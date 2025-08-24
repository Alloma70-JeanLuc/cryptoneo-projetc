--liquibase formatted sql

--changeset alloma:20250824-03-create-books-sequence
--comment Create sequence for books table

CREATE SEQUENCE books_SEQ START WITH 1 INCREMENT BY 50;
