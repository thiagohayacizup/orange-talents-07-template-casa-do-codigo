package br.com.projeto.casa.codigo.estado.controlador;

import br.com.projeto.casa.codigo.estado.modelo.Estado;
import br.com.projeto.casa.codigo.estado.repositorio.EstadoRepositorio;
import br.com.projeto.casa.codigo.pais.modelo.Pais;
import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;

import javax.validation.constraints.NotBlank;

class EstadoRequisicao {

    @NotBlank
    private final String nome;

    @NotBlank
    private final String pais;

    EstadoRequisicao(final String nome, final String pais) {
        this.nome = nome;
        this.pais = pais;
    }

    EstadoResposta cadastrar(final EstadoRepositorio estadoRepositorio, final PaisRepositorio paisRepositorio ){
        return new EstadoResposta(
                new Estado(nome, Pais.buscarPorNome( pais, paisRepositorio ) )
                        .cadastrar( estadoRepositorio )
        );
    }

}
