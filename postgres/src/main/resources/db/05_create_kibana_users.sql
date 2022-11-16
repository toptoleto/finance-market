create role finance_market_kibana;
create user kibana login password 'kibana' in role finance_market_kibana;
grant usage on schema finance_market to finance_market_kibana;
alter user kibana set search_path = finance_market;