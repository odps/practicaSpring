-- Base de datos de tienda:

-- Descripcion de productos en venta
CREATE TABLE op_products (
product_pk INT GENERATED ALWAYS AS IDENTITY,
product_name VARCHAR(25) NOT NULL,
product_description VARCHAR(50),
product_price FLOAT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(product_pk)
);

-- Roles para usuario (en un principio admin y cliente) 
CREATE TABLE op_roles(
role_pk INT GENERATED ALWAYS AS IDENTITY,
role_name VARCHAR(15),
PRIMARY KEY(role_pk)
);

-- Permisos asignados
CREATE TABLE op_permissions(
permission_pk INT GENERATED ALWAYS AS IDENTITY,
permission_type VARCHAR(25),
PRIMARY KEY(permission_pk)
);

--Relacion Permisos y Roles
CREATE TABLE op_roles_access(
role_access_pk INT GENERATED ALWAYS AS IDENTITY,
role_id INT NOT NULL,
permission_id INT NOT NULL,
PRIMARY KEY(role_access_pk),
FOREIGN KEY(role_id) REFERENCES op_roles(role_pk),
FOREIGN KEY(permission_id) REFERENCES op_permissions(permission_pk)
);

-- Tabla de usuarios
CREATE TABLE op_users (
user_pk INT GENERATED ALWAYS AS IDENTITY,
user_firstname VARCHAR(50),
user_lastname VARCHAR(50),
user_email VARCHAR(50),
username VARCHAR(25) NOT NULL,
user_password VARCHAR(255) NOT NULL,
user_role INT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(user_pk),
FOREIGN KEY(user_role) REFERENCES op_roles(role_pk) ON DELETE SET NULL,
CONSTRAINT unique_username UNIQUE (username),
CONSTRAINT unique_email UNIQUE (user_email)
);

-- Tabla que contiene los datos de la tienda que realiza la venta.
CREATE TABLE op_shops (
shop_pk INT GENERATED ALWAYS AS IDENTITY,
shop_name VARCHAR(50),
shop_owner INT NOT NULL,
shop_adress VARCHAR(100),
shop_email VARCHAR(50),
shop_phone VARCHAR(15),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(shop_pk),
FOREIGN KEY(shop_owner) REFERENCES op_users(user_pk),
CONSTRAINT unique_shopname UNIQUE (shop_name)
);

-- Stock de las tiendas
CREATE TABLE op_stock (
stock_pk INT GENERATED ALWAYS AS IDENTITY,
stock_owner INT NOT NULL,
stock_product INT NOT NULL,
stock_quantity INT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(stock_pk),
FOREIGN KEY(stock_owner) REFERENCES op_shops(shop_pk) ON DELETE CASCADE,
FOREIGN KEY(stock_product) REFERENCES op_products(product_pk) ON DELETE CASCADE
);

--Tabla de pedidos
CREATE TABLE op_orders (
order_pk INT GENERATED ALWAYS AS IDENTITY,
order_shop INT NOT NULL,
order_product INT NOT NULL,
order_quantity INT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(order_pk),
FOREIGN KEY(order_shop) REFERENCES op_shops(shop_pk),
FOREIGN KEY(order_product) REFERENCES op_products(product_pk) ON DELETE SET NULL
);

-- Tabla de facturas, generadas cuando se realice una compra por parte del cliente.
CREATE TABLE op_invoices (
invoice_pk INT GENERATED ALWAYS AS IDENTITY,
user_id INT NOT NULL,
order_id INT NOT NULL,
invoice_total FLOAT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(invoice_pk),
FOREIGN KEY(user_id) REFERENCES op_users(user_pk),
FOREIGN KEY(order_id) REFERENCES op_orders(order_pk)
);


-- DROP TABLE op_invoices;
-- DROP TABLE op_orders;
-- DROP TABLE op_stock;
-- DROP TABLE op_shops;
-- DROP TABLE op_users;
-- DROP TABLE op_permissions;
-- DROP TABLE op_roles;
-- DROP TABLE op_products;