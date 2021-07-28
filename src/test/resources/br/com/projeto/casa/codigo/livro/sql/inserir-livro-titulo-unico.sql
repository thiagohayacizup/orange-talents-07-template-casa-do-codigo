INSERT INTO Autor (nome, email, descricao, data_cadastro)
VALUES ('Joao Silva', 'joao.silva@email.com', 'descricao', NOW());

INSERT INTO Categoria(nome) VALUES ('Aventura');

INSERT INTO Livro(titulo, resumo, sumario, preco, numero_paginas, isbn, data_lancamento, autor_id, categoria_id)
VALUES ('Contos', '', '', 50.0, 150, 'ISBN 467-03776', '2022-01-01', 1, 1);