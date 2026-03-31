CREATE TABLE IF NOT EXISTS  products(
    id uuid primary key,
    name varchar(100) not null,
    description varchar(255),
    price DECIMAL(10, 2) not null,
    created_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP(6),
    deleted_at TIMESTAMP(6)
);