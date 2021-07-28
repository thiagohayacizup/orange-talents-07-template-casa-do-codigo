package br.com.projeto.casa.codigo.categoria.controlador;

import br.com.projeto.casa.codigo.categoria.modelo.Categoria;
import br.com.projeto.casa.codigo.categoria.repositorio.CategoriaRepositorio;

import javax.validation.constraints.NotBlank;

public class CategoriaRequisicaoLivro {

    public static final String NOME_INVALIDO = "Nome da categoria invalido.";

    @NotBlank( message = NOME_INVALIDO )
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria buscarCategoriaPorNome( final CategoriaRepositorio categoriaRepositorio ){
        return Categoria.buscarPorNome( nome, categoriaRepositorio );
    }

}
