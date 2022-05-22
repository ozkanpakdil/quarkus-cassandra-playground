CREATE TABLE IF NOT EXISTS test.customer (
  creation_date date,
  customer_number text,
  description text,
  state text,
  PRIMARY KEY (customer_number, creation_date)
);

