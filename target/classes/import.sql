INSERT INTO roles (name, code, is_active, created_at, deleted, deleted_at, updated_at)
VALUES
('USER', 'ROLE-USER', true, CURRENT_TIMESTAMP, false, NULL, NULL),
('SUPERUSER', 'ROLE-SUPERUSER', true, CURRENT_TIMESTAMP, false, NULL, NULL),
('ADMIN', 'ROLE-ADMIN', true, CURRENT_TIMESTAMP, false, NULL, NULL),
('ROOT', 'ROLE-ROOT', true, CURRENT_TIMESTAMP, false, NULL, NULL);
