package br.com.projeto.casa.codigo.livro.controlador;

import br.com.projeto.casa.codigo.livro.modelo.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

class LivroDetalhadoResposta {

    private final Livro livro;

    LivroDetalhadoResposta(final Livro livro) {
        this.livro = livro;
    }

    public String getTitulo() {
        return livro.getTitulo();
    }

    public String getIsbn() {
        return livro.getIsbn();
    }

    public String getResumo() {
        return livro.getResumo();
    }

    public String getSumario() {
        return livro.getSumario();
    }

    public BigDecimal getPreco() {
        return livro.getPreco();
    }

    public Integer getNumeroPaginas() {
        return livro.getNumeroPaginas();
    }

    public LocalDate getDataLancamento() {
        return livro.getDataLancamento();
    }

    public String getCategoria() {
        return livro.getCategoria();
    }

    public String getAutor() {
        return livro.getAutor();
    }

}
