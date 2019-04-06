CREATE TYPE colour AS ENUM ('red', 'blue', 'black', 'silver', 'green', 'purple', 'white');
CREATE TYPE style AS ENUM ('road', 'mountain_hard_tail', 'mountain_full_sus', 'hybrid');

CREATE TABLE customer (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR (50) NOT NULL,
  last_name VARCHAR (50) NOT NULL,
  email VARCHAR (150) UNIQUE NOT NULL,
);

CREATE TABLE bike (
  id INT AUTO_INCREMENT PRIMARY KEY,
  make VARCHAR (50) NOT NULL,
  model VARCHAR (50) NOT NULL,
  colour ENUM ('red', 'blue', 'black', 'silver', 'green', 'purple', 'white') NOT NULL,
  style ENUM ('road', 'mountain_hard_tail', 'mountain_full_sus', 'hybrid') NOT NULL,
  price NUMERIC NOT NULL,
  stock INTEGER DEFAULT 0,
);

CREATE TABLE customer_order (
  id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INTEGER NOT NULL REFERENCES customer(id),
  bike_id INTEGER NOT NULL REFERENCES bike(id),
  order_date TIMESTAMP NOT NULL,
  total_price NUMERIC NOT NULL
);

CREATE TABLE repair_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INTEGER NOT NULL REFERENCES customer(id),
  make VARCHAR (50),
  model VARCHAR (50),
  colour ENUM ('red', 'blue', 'black', 'silver', 'green', 'purple', 'white'),
  description VARCHAR (255)
);

CREATE TABLE employee (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR (50) NOT NULL,
  last_name VARCHAR (50) NOT NULL
);

CREATE TABLE repair (
  id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INTEGER NOT NULL REFERENCES customer(id),
  repair_item_id INTEGER NOT NULL REFERENCES repair_item(id),
  description VARCHAR (255),
  repair_date TIMESTAMP NOT NULL,
  cost NUMERIC NOT NULL,
  employee_id INTEGER NOT NULL REFERENCES employee(id)
);