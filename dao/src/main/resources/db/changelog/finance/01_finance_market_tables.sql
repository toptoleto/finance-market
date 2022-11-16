--liquibase formatted sql

--changeset Munin.D.A:16.11.2022:client logicalfilepath:db.changelog/client/01_finance_market_tables.sql runAlways:true
----------------------------------------------------------------------------------------------------------
create table client
(
    id          int  not null primary key,
    surname     text null,
    first_name  text null,
    middle_name text null
);

comment on table client is 'Пользователь';

comment on column client.id is 'идентификатор';
comment on column client.surname is 'фамилия';
comment on column client.first_name is 'имя';
comment on column client.surname is 'отчество';

--rollback drop table if exists client;