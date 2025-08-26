CREATE TABLE IF NOT EXISTS pedidos_produtos (
    pedido_id BIGSERIAL  NOT NULL,
    produto_id BIGSERIAL  NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
