set role postgres;

alter default privileges in schema ext grant execute on functions to "as_TUZ";
alter default privileges in schema ext grant select on tables to "as_TUZ";
alter default privileges in schema ext grant all on types to "as_TUZ";
alter default privileges in schema ext grant select on tables to as_admin_read;
alter default privileges in schema ext grant execute on functions to as_admin_read;

alter default privileges in schema ext grant execute on functions to as_admin;
alter default privileges in schema ext grant select on tables to as_admin;
alter default privileges in schema ext grant all on types to as_admin;

create extension pgcrypto with schema ext;