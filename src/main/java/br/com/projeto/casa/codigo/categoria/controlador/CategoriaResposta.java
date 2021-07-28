package br.com.projeto.casa.codigo.categoria.controlador;

import br.com.projeto.casa.codigo.categoria.modelo.Categoria;

class CategoriaResposta {

    private final Categoria categoria;

    CategoriaResposta(final Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome(){
        return categoria.getNome();
    }

}
