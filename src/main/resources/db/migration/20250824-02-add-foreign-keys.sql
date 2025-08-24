--liquibase formatted sql

--changeset alloma:20250824-02-add-foreign-keys
--comment Add foreign key constraints on accounts, book_loans, libraries, role_permissions

ALTER TABLE accounts ADD CONSTRAINT FKl0culb6vcoliq6empnxv8brig FOREIGN KEY (person_id) REFERENCES persons;
ALTER TABLE accounts ADD CONSTRAINT FKt3wava8ssfdspnh3hg4col3m1 FOREIGN KEY (role_id) REFERENCES roles;
ALTER TABLE book_loans ADD CONSTRAINT FKo6d3xcdy9r2k3qbi4c84p9b6g FOREIGN KEY (book_id) REFERENCES books;
ALTER TABLE libraries ADD CONSTRAINT FK9jjqo3inhwtu3qsb0ufmha1h5 FOREIGN KEY (city_id) REFERENCES cities;
ALTER TABLE role_permissions ADD CONSTRAINT FKegdk29eiy7mdtefy5c7eirr6e FOREIGN KEY (permission_id) REFERENCES permissions;
ALTER TABLE role_permissions ADD CONSTRAINT FKn5fotdgk8d1xvo8nav9uv3muc FOREIGN KEY (role_id) REFERENCES roles;
