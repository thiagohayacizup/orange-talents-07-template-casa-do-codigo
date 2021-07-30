package br.com.projeto.casa.codigo.estado.controlador;

import br.com.projeto.casa.codigo.estado.modelo.Estado;

class EstadoResposta {

    private final Estado estado;

    EstadoResposta(final Estado estado) {
        this.estado = estado;
    }

    public String getNome(){
        return estado.getNome();
    }

    public String getPais(){
        return estado.getPais();
    }

}
