create role as_admin;
create user finance_market_admin with password 'finance_market_admin' in role as_admin;
create role "as_TUZ";
create role as_admin_read;

create user finance_market_app with password 'finance_market_app' in role "as_TUZ";

alter role finance_market_admin set search_path to finance_market,ext,information_schema;
alter role finance_market_app set search_path to finance_market,ext,information_schema;
alter role as_admin set search_path to finance_market,ext,information_schema;
alter role "as_TUZ" set search_path to finance_market,ext,information_schema;
alter role as_admin_read set search_path to finance_market,ext,information_schema;