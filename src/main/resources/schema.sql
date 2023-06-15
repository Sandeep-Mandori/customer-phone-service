-- We already have tables created by now.
-- so just running dummy select statement to workaround the springboot-hibernate autoloader
-- issue in schema creation that does not allow data upload if schema.sql is empty.

select * from customer;