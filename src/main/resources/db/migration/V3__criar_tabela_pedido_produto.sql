CREATE TABLE pedidos_produtos (
    pedido_id UUID  NOT NULL,
    produto_id UUID  NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
