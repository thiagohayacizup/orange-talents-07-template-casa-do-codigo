package br.com.projeto.casa.codigo.livro.controlador;

import br.com.projeto.casa.codigo.autor.controlador.AutorRequisicaoLivro;
import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;
import br.com.projeto.casa.codigo.categoria.controlador.CategoriaRequisicaoLivro;
import br.com.projeto.casa.codigo.categoria.repositorio.CategoriaRepositorio;
import br.com.projeto.casa.codigo.livro.modelo.Livro;
import br.com.projeto.casa.codigo.livro.repositorio.LivroRepositorio;
import br.com.projeto.casa.codigo.validador.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

class LivroRequisicao {

    private final Livro.Builder builder;

    LivroRequisicao() {
        this.builder = Livro.builder();
    }

    void setTitulo( final String titulo ){
        this.titulo = titulo;
    }

    @NotBlank
    @ValorUnico( campo = "titulo", dominio = Livro.class, message = "Titulo deve ser unico.")
    private String titulo;

    void setResumo( @NotBlank final String resumo ){
        builder.comResumo( resumo );
    }

    void setSumario( @NotBlank final String sumario ){
        builder.comSumario( sumario );
    }

    void setPreco( @NotNull final BigDecimal preco ){
        builder.comPreco( preco );
    }

    void setNumeroPaginas( @NotNull final Integer numeroPaginas ){
        builder.comNumeroPaginas( numeroPaginas );
    }

    void setIsbn( final String isbn ){
        this.isbn = isbn;
    }

    @NotBlank
    @ValorUnico( campo = "isbn", dominio = Livro.class, message = "Isbn deve ser unico." )
    private String isbn;

    void setDataLancamento( @NotNull final LocalDate dataLancamento ){
        builder.comDataLancamento( dataLancamento );
    }

    private CategoriaRequisicaoLivro categoriaRequisicao;

    void setCategoria( @NotNull final CategoriaRequisicaoLivro categoria ){
        this.categoriaRequisicao = categoria;
    }

    private AutorRequisicaoLivro autorRequisicao;

    void setAutor( @NotNull final AutorRequisicaoLivro autor ){
        this.autorRequisicao = autor;
    }

    public LivroResposta cadastrar(final LivroRepositorio repositorio, final CategoriaRepositorio categoriaRepositorio, final AutorRepositorio autorRepositorio ){
        return new LivroResposta(
                builder.comCategoria( categoriaRequisicao.buscarCategoriaPorNome( categoriaRepositorio ) )
                        .comTitulo( titulo )
                        .comIsbn( isbn )
                        .comAutor( autorRequisicao.buscarAutorPorEmail( autorRepositorio ) )
                        .construir()
                        .cadastrar( repositorio )
        );
    }

}
