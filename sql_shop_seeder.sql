-- INSERT INTO table_name (field1,field2) VALUES ('f1', 'f2')

-- INSERT INTO table
-- (column1, column2, ... column_n )
-- SELECT expression1, expression2, ... expression_n
-- FROM source_table
-- [WHERE conditions];

-- ---------------------------------
-- Tiendas

INSERT INTO op_shops
(
 shop_name,
 shop_adress,
 shop_email,
 shop_phone
)
VALUES
(
'Aliexpress',
'Carrer de Xina 12',
'ali@express.com',
123456789
);

INSERT INTO op_shops
(
 shop_name,
 shop_adress,
 shop_email,
 shop_phone
)
VALUES
(
'Amazon',
'Carrer de USA 17',
'shop@amzn.com',
987456321
);

INSERT INTO op_shops
(
 shop_name,
 shop_adress,
 shop_email,
 shop_phone
)
VALUES
(
'Ebay',
'Carrer de mi casa 22',
'shop@ebay.com',
58467231
);

-- ---------------------------------
-- Roles de los usuarios

INSERT INTO op_roles
(
    role_name
)
VALUES
(
'client'
);

INSERT INTO op_roles
(
    role_name
)
VALUES
(
'shop'
);

INSERT INTO op_roles
(
    role_name
)
VALUES
(
'admin'
);

-- ---------------------------------
-- Productos

-- Productos de aliexpress
INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Audifonos',
'Para escuchar musica.',
20,
50,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%express%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Telefono',
'Para llamar a tus amigos.',
120,
150,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%express%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Alfombra',
'Para volar por arabia, 100% pelo de camello.',
120,
150,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%express%' FETCH NEXT 1 ROWS ONLY)
);

-- Productos de Amazon

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Audifonos',
'Para escuchar musica.',
100,
70,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%azon%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Telefono',
'Para llamar a tus amigos.',
220,
150,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%azon%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Bicicleta BMX',
'Para hacer deporte.',
220,
150,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%azon%' FETCH NEXT 1 ROWS ONLY)
);

-- Productos de Ebay
INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Audifonos',
'Para escuchar musica.',
10,
30,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%bay%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Telefono',
'Para llamar a tus amigos.',
40,
150,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%bay%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_products
(
product_name,
product_description,
product_price,
product_stock,
product_vendor
)
VALUES
(
'Aceita Oliva',
'80% Natural, el otro 20% no se sabe.',
40,
150,
(SELECT shop_pk FROM op_shops WHERE shop_name LIKE '%bay%' FETCH NEXT 1 ROWS ONLY)
);

-- ---------------------------------
-- Usuarios

INSERT INTO op_users
(
user_firstname,
user_lastname,
user_email,
username,
user_password,
user_role
)
VALUES 
(
'Juan',
'Perez',
'test@mail.com',
'juanpe',
'password',
(SELECT role_pk FROM op_roles WHERE role_name LIKE '%client%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_users
(
user_firstname,
user_lastname,
user_email,
username,
user_password,
user_role
)
VALUES 
(
'John',
'Wick',
'admin@mail.com',
'admin',
'admin',
(SELECT role_pk FROM op_roles WHERE role_name LIKE '%admin%' FETCH NEXT 1 ROWS ONLY)
);

INSERT INTO op_users
(
user_firstname,
user_lastname,
user_email,
username,
user_password,
user_role
)
VALUES 
(
'Elon',
'Musk',
'test1@mail.com',
'molusk',
'password',
(SELECT role_pk FROM op_roles WHERE role_name LIKE '%shop%' FETCH NEXT 1 ROWS ONLY)
);

-- ---------------------------------
-- Recibos

INSERT INTO op_invoices
(
shop_id,
user_id,
product_id,
product_quantity,
invoice_total
)
VALUES
(
2,
4,
10,
2,
200
);