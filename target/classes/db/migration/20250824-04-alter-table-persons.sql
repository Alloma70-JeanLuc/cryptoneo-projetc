--liquibase formatted sql

--changeset alloma:20250824-04-alter-persons
--comment alter column for persons table

alter table if exists persons add column first_name varchar(255);
alter table if exists persons add column last_name varchar(255);