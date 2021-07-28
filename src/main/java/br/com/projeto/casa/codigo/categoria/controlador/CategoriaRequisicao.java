package br.com.projeto.casa.codigo.categoria.controlador;

import br.com.projeto.casa.codigo.categoria.modelo.Categoria;
import br.com.projeto.casa.codigo.categoria.repositorio.CategoriaRepositorio;
import br.com.projeto.casa.codigo.validador.ValorUnico;

import javax.validation.constraints.NotBlank;

class CategoriaRequisicao {

    public static final String NOME_INVALIDO = "Nome da categoria invalido.";

    @NotBlank( message = NOME_INVALIDO )
    @ValorUnico( campo = "nome", dominio = Categoria.class )
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaResposta cadastrar(final CategoriaRepositorio categoriaRepositorio ){
        return new CategoriaResposta(
                new Categoria( nome ).cadastrar( categoriaRepositorio )
        );
    }

}
