-- En oracle DB se pueden insertar usando:
-- INSERT INTO table_name (field1,field2) VALUES ('f1', 'f2')
-- O si se quiere insertar de forma multiple:
-- INSERT INTO table
-- (column1, column2, ... column_n )
-- SELECT expression1, expression2, ... expression_n
-- FROM source_table
-- [WHERE conditions];

-- ---------------------------------

-- Insertar roles
INSERT INTO op_roles (role_name) VALUES ('ADMIN');
INSERT INTO op_roles (role_name) VALUES ('CLIENT');
INSERT INTO op_roles (role_name) VALUES ('SHOP_OWNER');

-- Insertar permisos
INSERT INTO op_permissions (permission_type) VALUES ('CREATE_PRODUCT');
INSERT INTO op_permissions (permission_type) VALUES ('UPDATE_PRODUCT');
INSERT INTO op_permissions (permission_type) VALUES ('DELETE_PRODUCT');
INSERT INTO op_permissions (permission_type) VALUES ('VIEW_ALL_ORDERS');
INSERT INTO op_permissions (permission_type) VALUES ('PLACE_ORDER');
INSERT INTO op_permissions (permission_type) VALUES ('MANAGE_STOCK');
INSERT INTO op_permissions (permission_type) VALUES ('MANAGE_USERS');

-- Asignar permisos a roles
-- Admin tiene todos los permisos
INSERT INTO op_roles_access (role_id, permission_id) 
SELECT 1, permission_pk FROM op_permissions;

-- Cliente solo puede hacer pedidos
INSERT INTO op_roles_access (role_id, permission_id)
SELECT 2, permission_pk FROM op_permissions WHERE permission_type = 'PLACE_ORDER';

-- Shop owner puede gestionar productos y stock
INSERT INTO op_roles_access (role_id, permission_id)
SELECT 3, permission_pk FROM op_permissions 
WHERE permission_type IN ('CREATE_PRODUCT', 'UPDATE_PRODUCT', 'MANAGE_STOCK', 'VIEW_ALL_ORDERS');

-- Insertar usuarios (password: hashed version of 'password123')
INSERT INTO op_users (user_firstname, user_lastname, user_email, username, user_password, user_role)
VALUES ('Admin', 'User', 'admin@example.com', 'admin', 'password123', 1);

INSERT INTO op_users (user_firstname, user_lastname, user_email, username, user_password, user_role)
VALUES ('Shop', 'Owner', 'shop@example.com', 'shopowner', 'password123', 3);

INSERT INTO op_users (user_firstname, user_lastname, user_email, username, user_password, user_role)
VALUES ('Client', 'User', 'client@example.com', 'client', 'password123', 2);

-- Insertar tiendas
INSERT INTO op_shops (shop_name, shop_owner, shop_adress, shop_email, shop_phone)
VALUES ('Tech Shop', 2, 'Calle Principal 123', 'contact@techshop.com', '555-123-4567');

-- Insertar productos
INSERT INTO op_products (product_name, product_description, product_price)
VALUES ('Laptop', 'Potente laptop para gaming', 1299.99);

INSERT INTO op_products (product_name, product_description, product_price)
VALUES ('Smartphone', 'Último modelo con cámara 64MP', 799.99);

INSERT INTO op_products (product_name, product_description, product_price)
VALUES ('Tablet', 'Ligera y potente', 349.99);

INSERT INTO op_products (product_name, product_description, product_price)
VALUES ('Monitor', 'Monitor 4K de 32 pulgadas', 499.99);

-- Insertar stock
INSERT INTO op_stock (stock_owner, stock_product, stock_quantity)
VALUES (1, 1, 10); -- 10 laptops

INSERT INTO op_stock (stock_owner, stock_product, stock_quantity)
VALUES (1, 2, 20); -- 20 smartphones

INSERT INTO op_stock (stock_owner, stock_product, stock_quantity)
VALUES (1, 3, 15); -- 15 tablets

INSERT INTO op_stock (stock_owner, stock_product, stock_quantity)
VALUES (1, 4, 8);  -- 8 monitors

-- Insertar pedidos de ejemplo
INSERT INTO op_orders (order_shop, order_product, order_quantity)
VALUES (1, 1, 1); -- 1 laptop

INSERT INTO op_orders (order_shop, order_product, order_quantity)
VALUES (1, 2, 2); -- 2 smartphones

-- Insertar facturas
INSERT INTO op_invoices (user_id, order_id, invoice_total)
VALUES (3, 1, 1299.99); -- factura por 1 laptop para el cliente

INSERT INTO op_invoices (user_id, order_id, invoice_total)
VALUES (3, 2, 1599.98); -- factura por 2 smartphones para el cliente