-- ======================================
        -- Changeset 1 : Création de la table authors
        -- ======================================
        CREATE TABLE authors (
        id BIGSERIAL PRIMARY KEY,
        firstName VARCHAR(255),
        lastName VARCHAR(255),
        email VARCHAR(255) NOT NULL UNIQUE,
        biography TEXT,
        createdAt TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
        updatedAt TIMESTAMPTZ,
        active BOOLEAN DEFAULT TRUE,
        deleted BOOLEAN DEFAULT FALSE,
        deletedAt TIMESTAMPTZ
        );

         -- Création de la séquence pour les IDs des livres (attendu par Panache)
         CREATE SEQUENCE authors_SEQ start with 1 increment by 50;
        -- ======================================
        -- Changeset 2 : Création de la table books
        -- ======================================
        CREATE TABLE books (
        id BIGSERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        isbn VARCHAR(20),
        description TEXT,
        publicationYear INT,
        author_id BIGINT NOT NULL,
        url VARCHAR(255),
        publicId VARCHAR(255),
        format VARCHAR(50),
        mimetype VARCHAR(100),
        createdAt TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
        updatedAt TIMESTAMPTZ,
        active BOOLEAN DEFAULT TRUE,
        deleted BOOLEAN DEFAULT FALSE,
        deletedAt TIMESTAMPTZ,
        CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES authors(id)
        );

        -- Index sur la FK pour accélérer les requêtes
        CREATE INDEX idx_book_author_id ON books(author_id);
        -- Création de la séquence pour les IDs des livres (attendu par Panache)
        CREATE SEQUENCE books_SEQ start with 1 increment by 50;
