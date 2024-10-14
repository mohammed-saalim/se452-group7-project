-- Products and Categories (@author: Sai Rachana Venna):
-- Insert data into the category table
INSERT INTO category (name) VALUES 
('Clothing'),
('Electronics'),
('Home and Kitchen');
-- Insert data into the product table
INSERT INTO product (name, description, price, category_id) VALUES 
('T-Shirt', 'Comfortable linen T-shirt', 9.99, 1),
('Jeans', 'Stylish and durable denim jeans',39.99, 1),
('Shirt', 'Formal cotton Shirt', 25.99, 1),
('Laptop', 'Lightweight and powerful laptop for professional work', 1299.99, 2),
('Smartphone', 'Latest model with all new advanced features', 899.99, 2),
('Headphones', 'Wireless with noise cancellation', 199.99, 2),
('Blender', 'High-performance kitchen blender', 69.99, 3),
('Coffee Maker', 'Brew coffee with ease using this automatic coffee maker', 99.99, 3),
('Microwave Oven', 'Countertop Microwave in stainless steel', 169.99, 3);

-- Accounts (@author: Bhumika Ramesh):
-- Insert Accounts
INSERT INTO account (id, username, password)
VALUES
(1, 'john_doe', 'password123'),
(2, 'jane_smith', 'securepassword');
 
-- Insert Customers
INSERT INTO customer (customer_id, account_id, first_name, last_name, email)
VALUES
(1, 1, 'John', 'Doe', 'john.doe@example.com'),
(2, 2, 'Jane', 'Smith', 'jane.smith@example.com');

-- Orders (@author: Mohammed Saalim Kartapillai):

-- Insert data into the customer_order table
INSERT INTO customer_order (order_date, total_amount, customer_id) 
VALUES ('2024-10-01', 59.99, 1), 
       ('2024-10-02', 1299.99, 2);

-- Insert data into the cart table
INSERT INTO cart (total_amount, customer_id) 
VALUES (59.99, 1), 
       (1299.99, 2);

-- Insert data into the cart_item table
INSERT INTO cart_item (quantity, price, cart_id, product_id) 
VALUES (1, 59.99, 1, 1), 
       (1, 1299.99, 2, 4);

-- Shipping (@author: Pritiv Janarthanan):

-- Insert data into the shipping table
INSERT INTO shipping (status, tracking_number, order_id) VALUES
('Pending', 'TRK123456789', 1),
('Shipped', 'TRK987654321', 2);
