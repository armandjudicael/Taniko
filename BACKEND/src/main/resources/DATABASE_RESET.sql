TRUNCATE TABLE company cascade;
TRUNCATE TABLE filiale_tenant_user;
TRUNCATE TABLE person cascade ;
TRUNCATE TABLE tenant_user cascade ;
TRUNCATE TABLE company_datasource_config cascade ;
delete from admin where username<>'armand_judicael';
