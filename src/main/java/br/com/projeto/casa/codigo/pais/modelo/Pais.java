package br.com.projeto.casa.codigo.pais.modelo;

import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public
class Pais {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    private String nome;

    private Pais(){}

    public Pais(final String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Pais cadastrar( final PaisRepositorio paisRepositorio ) {
        return paisRepositorio.save( this );
    }
}
