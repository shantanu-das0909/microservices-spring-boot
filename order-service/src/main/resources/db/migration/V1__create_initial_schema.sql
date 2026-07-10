CREATE TABLE "t_order" (
    id UUID PRIMARY KEY,
    sku_code VARCHAR(255),
    price NUMERIC(38, 2),
    quantity INTEGER
);