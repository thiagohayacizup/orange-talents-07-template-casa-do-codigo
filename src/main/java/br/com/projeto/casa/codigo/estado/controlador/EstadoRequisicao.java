package br.com.projeto.casa.codigo.estado.controlador;

import br.com.projeto.casa.codigo.estado.modelo.Estado;
import br.com.projeto.casa.codigo.estado.repositorio.EstadoRepositorio;
import br.com.projeto.casa.codigo.pais.modelo.Pais;
import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

class EstadoRequisicao {

    @NotBlank
    private final String nome;

    @NotNull
    private final Long paisId;

    EstadoRequisicao(final String nome, final Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    EstadoResposta cadastrar(final EstadoRepositorio estadoRepositorio, final PaisRepositorio paisRepositorio ){
        return new EstadoResposta(
                new Estado(nome, Pais.buscarPorId( paisId, paisRepositorio ) )
                        .cadastrar( estadoRepositorio )
        );
    }

}
