package br.com.projeto.casa.codigo.pais.controlador;

import br.com.projeto.casa.codigo.pais.modelo.Pais;

class PaisResposta {

    private final Pais pais;

    PaisResposta(final Pais pais) {
        this.pais = pais;
    }

    public String getNome() {
        return pais.getNome();
    }

}
