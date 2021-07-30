package br.com.projeto.casa.codigo.pais.controlador;

import br.com.projeto.casa.codigo.pais.modelo.Pais;
import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;
import br.com.projeto.casa.codigo.validador.ValorUnico;

import javax.validation.constraints.NotBlank;

class PaisRequisicao {

    @NotBlank
    @ValorUnico(
            campo = "nome",
            dominio = Pais.class,
            message = "Pais deve ser unico."
    )
    private String nome;

    void setNome( final String nome ){
        this.nome = nome;
    }

    PaisResposta cadastrar( final PaisRepositorio paisRepositorio ){
        return new PaisResposta(
                new Pais( nome ).cadastrar( paisRepositorio )
        );
    }

}
