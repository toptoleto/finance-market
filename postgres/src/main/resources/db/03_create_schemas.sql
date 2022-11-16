create schema finance_market authorization as_admin;
create schema ext authorization as_admin;

grant usage on schema finance_market to "as_TUZ";
grant usage on schema finance_market to as_admin_read;
grant usage on schema finance_market to finance_market_admin;
grant create on schema finance_market to finance_market_admin;
grant usage on schema finance_market to as_admin_read;
grant usage on schema ext to "as_TUZ";
grant usage on schema ext to as_admin_read;

alter default privileges for role as_admin in schema finance_market grant select on tables to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant insert on tables to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant delete on tables to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant update on tables to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant execute on functions to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant all on sequences to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant all on types to "as_TUZ";
alter default privileges for role as_admin in schema finance_market grant select on tables to as_admin_read;
alter default privileges for role as_admin in schema finance_market grant execute on functions to as_admin_read;
alter default privileges for role as_admin in schema finance_market grant usage on sequences to as_admin_read;

alter default privileges for role as_admin in schema ext grant select on tables to as_admin_read;
alter default privileges for role as_admin in schema ext grant execute on functions to as_admin_read;
alter default privileges for role as_admin in schema ext grant all on types to as_admin_read;

alter default privileges for role as_admin in schema ext grant select on tables to as_admin_read;
alter default privileges for role as_admin in schema ext grant execute on functions to as_admin_read;
alter default privileges for role as_admin in schema ext grant all on types to as_admin_read;