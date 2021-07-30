package br.com.projeto.casa.codigo.cliente.controlador;

import br.com.projeto.casa.codigo.cliente.modelo.Cliente;

class ClienteResposta {

    private final Cliente cliente;

    ClienteResposta(final Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId(){
        return cliente.getId();
    }

}
