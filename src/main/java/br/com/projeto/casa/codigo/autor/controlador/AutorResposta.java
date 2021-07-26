package br.com.projeto.casa.codigo.autor.controlador;

import br.com.projeto.casa.codigo.autor.modelo.Autor;

class AutorResposta {

    private final Autor autor;

    AutorResposta( final Autor autor ) {
        this.autor = autor;
    }

    public String getNome() {
        return autor.getNome();
    }

    public String getEmail() {
        return autor.getEmail();
    }

}
