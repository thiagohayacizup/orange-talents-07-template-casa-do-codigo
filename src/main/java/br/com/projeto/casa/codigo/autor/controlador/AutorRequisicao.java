package br.com.projeto.casa.codigo.autor.controlador;

import br.com.projeto.casa.codigo.autor.modelo.Autor;
import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;
import br.com.projeto.casa.codigo.validador.ValorUnico;

import javax.validation.constraints.NotBlank;

class AutorRequisicao {

    public static final String NOME_INVALIDO = "Nome do autor invalido.";
    public static final String EMAIL_INVALIDO = "Email do autor invalido.";
    public static final String DESCRICAO_INVALIDO = "Descricao do autor invalido.";

    @NotBlank( message = NOME_INVALIDO )
    private final String nome;

    @NotBlank( message = EMAIL_INVALIDO )
    @ValorUnico( campo = "email", dominio = Autor.class )
    private final String email;

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
