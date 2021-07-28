package br.com.projeto.casa.codigo.autor.controlador;

import br.com.projeto.casa.codigo.autor.modelo.Autor;
import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;

import javax.validation.constraints.NotBlank;

public class AutorRequisicaoLivro {

    public static final String NOME_INVALIDO = "Nome do autor invalido.";
    public static final String EMAIL_INVALIDO = "Email do autor invalido.";

    @NotBlank( message = NOME_INVALIDO )
    private final String nome;

    @NotBlank( message = EMAIL_INVALIDO )
    private final String email;

    AutorRequisicaoLivro(final String nome, final String email) {
        this.nome = nome;
        this.email = email;
    }

    public Autor buscarAutorPorEmail( final AutorRepositorio autorRepositorio ){
        return Autor.buscarPorEmail( email, autorRepositorio );
    }

}
