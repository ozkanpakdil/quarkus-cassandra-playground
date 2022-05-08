CREATE TABLE IF NOT EXISTS customer (
  id UUID,
  creation_date date,
  customer_number text,
  description text,
  state text,
  PRIMARY KEY ((id))
);