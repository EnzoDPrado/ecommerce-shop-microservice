CREATE TABLE IF NOT EXISTS  orders(
    id uuid primary key,
    user_id uuid not null,
    product_id uuid not null,
    quantity int not null,
    status varchar(50) not null,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_ORDERS_USERS FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT FK_ORDERS_PRODUCTS FOREIGN KEY (product_id) REFERENCES products(id)
);