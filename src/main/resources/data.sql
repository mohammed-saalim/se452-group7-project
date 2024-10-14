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

