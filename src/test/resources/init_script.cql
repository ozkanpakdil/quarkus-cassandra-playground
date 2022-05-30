CREATE KEYSPACE test WITH REPLICATION = {'class':'SimpleStrategy','replication_factor':3};

USE test;

CREATE TABLE customer (
customer_number text,
description text,
VERSION bigint,
creation_date date,
state text,
PRIMARY KEY(customer_number, creation_date)
) ;

insert into customer(customer_number, description, VERSION, creation_date,state)
values ('numero-uno', 'deacription1', 0, todate(now()), 's');