--liquibase formatted sql

--changeset alloma:20250824-01-add-unique-constraints
--comment Add unique constraints on accounts, book_loans, books, cities, libraries, permissions, persons, roles

ALTER TABLE accounts ADD CONSTRAINT UKp2jd4db8821l8voctujboa9oh UNIQUE (code);
ALTER TABLE accounts ADD CONSTRAINT UKn7ihswpy07ci568w34q0oi8he UNIQUE (email);
ALTER TABLE accounts ADD CONSTRAINT UK6ptacun6ln8wnpsd81vukio38 UNIQUE (person_id);

ALTER TABLE book_loans ADD CONSTRAINT UK637ct32hacuo8s3p8tobg51bp UNIQUE (code);

ALTER TABLE books ADD CONSTRAINT UKfodt4nn4uh16757aynkmd7q8c UNIQUE (code);

ALTER TABLE cities ADD CONSTRAINT UKqww1g66rmhx352jxut53oqh3y UNIQUE (code);
ALTER TABLE cities ADD CONSTRAINT UKl61tawv0e2a93es77jkyvi7qa UNIQUE (name);

ALTER TABLE libraries ADD CONSTRAINT UK6doofxojex2p52epy1l8v7gon UNIQUE (code);

ALTER TABLE permissions ADD CONSTRAINT UK7lcb6glmvwlro3p2w2cewxtvd UNIQUE (code);
ALTER TABLE permissions ADD CONSTRAINT UKpnvtwliis6p05pn6i3ndjrqt2 UNIQUE (name);

ALTER TABLE persons ADD CONSTRAINT UK56dj0doy4bke75hf1h1h4anpy UNIQUE (code);
ALTER TABLE persons ADD CONSTRAINT UK1x5aosta4bke75hf1h1h4anrd UNIQUE (email);

ALTER TABLE roles ADD CONSTRAINT UKch1113horj4qr56f91omojv8 UNIQUE (code);
ALTER TABLE roles ADD CONSTRAINT UKofx66keruapi6vyqpv6f2or37 UNIQUE (name);
