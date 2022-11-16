--liquibase formatted sql
--changeset Munin.D.A:22.04.00.:before_all_set_role_as_admin_pgse logicalFilePath:db.changelog/finance_market/00_set_role_as_admin.sql runAlways:true runOnChange:true failOnError:true

grant insert, select, update, delete, truncate on databasechangelog to as_admin;
grant insert, select, update, delete, truncate on databasechangeloglock to as_admin;

set role as_admin;
--rollback ;