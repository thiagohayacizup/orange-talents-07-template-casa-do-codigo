package br.com.projeto.casa.codigo.livro.controlador;

import br.com.projeto.casa.codigo.livro.modelo.Livro;

class LivroResposta {

    private final Livro livro;

    LivroResposta( final Livro livro ) {
        this.livro = livro;
    }

    public String getTitulo(){
        return livro.getTitulo();
    }

    public String getIsbn(){
        return livro.getIsbn();
    }

}
