CREATE TABLE produtos (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description VARCHAR(255) NOT NULL,
     category VARCHAR(255),
     preco_compra DOUBLE PRECISION NOT NULL,
     preco_venda DOUBLE PRECISION,
     stock INTEGER NOT NULL
);