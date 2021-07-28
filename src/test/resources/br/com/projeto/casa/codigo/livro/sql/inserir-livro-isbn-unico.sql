INSERT INTO Autor (nome, email, descricao, data_cadastro)
VALUES ('Joao Silveira', 'joao.silveira@email.com', 'descricao', NOW());

INSERT INTO Categoria(nome) VALUES ('Comedia');

INSERT INTO Livro(titulo, resumo, sumario, preco, numero_paginas, isbn, data_lancamento, autor_id, categoria_id)
VALUES ('Livro Nome', '', '', 50.0, 150, 'ISBN 467-5847-474-3375-03776', '2022-01-01', 1, 1);