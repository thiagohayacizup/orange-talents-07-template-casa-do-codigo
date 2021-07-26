package br.com.projeto.casa.codigo.autor.controlador;

import br.com.projeto.casa.codigo.autor.modelo.Autor;
import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

class AutorRequisicao {

    public static final String NOME_INVALIDO = "Nome do autor invalido.";
    public static final String EMAIL_INVALIDO = "Email do autor invalido.";
    public static final String DESCRICAO_INVALIDO = "Descricao do autor invalido.";

    @NotNull( message = NOME_INVALIDO)
    @NotEmpty( message = NOME_INVALIDO )
    @NotBlank( message = NOME_INVALIDO )
    private final String nome;

    @NotNull( message = EMAIL_INVALIDO)
    @NotEmpty( message = EMAIL_INVALIDO )
    @NotBlank( message = EMAIL_INVALIDO )
    private final String email;

    @NotNull( message = DESCRICAO_INVALIDO)
    @NotEmpty( message = DESCRICAO_INVALIDO )
    @NotBlank( message = DESCRICAO_INVALIDO )
    private final String descricao;

    AutorRequisicao(final String nome, final String email, final String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public AutorResposta cadastrar( final AutorRepositorio autorRepositorio ){
        return new AutorResposta(
                new Autor( nome, email, descricao ).cadastrar( autorRepositorio )
        );
    }

}
