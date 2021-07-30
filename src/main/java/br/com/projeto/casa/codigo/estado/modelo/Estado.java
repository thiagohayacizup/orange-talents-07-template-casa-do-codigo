package br.com.projeto.casa.codigo.estado.modelo;

import br.com.projeto.casa.codigo.estado.modelo.excessao.EstadoComPaisJaCadastradoException;
import br.com.projeto.casa.codigo.estado.modelo.excessao.EstadoNaoEncontradoException;
import br.com.projeto.casa.codigo.estado.repositorio.EstadoRepositorio;
import br.com.projeto.casa.codigo.pais.modelo.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    public static Estado buscarPorId(final Long id, final EstadoRepositorio estadoRepositorio ){
        return estadoRepositorio
                .findById( id )
                .orElseThrow( () -> new EstadoNaoEncontradoException(
                        String.format("Estado com id { %d } nao encontrado.", id)
                ));
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    @NotNull
    private Pais pais;

    private Estado(){}

    public Estado(final String nome, final Pais pais){
        this.nome = nome;
        this.pais = pais;
    }

    public Estado cadastrar( final EstadoRepositorio estadoRepositorio ){
        estadoRepositorio.findByNomeAndPais( nome, pais )
                .ifPresent( estado -> {
                    throw new EstadoComPaisJaCadastradoException(
                        String.format("Estado { %s } ja cadastrado e vinculado ao Pais { %s }.", nome, pais.getNome() )
                    );
                });
        return estadoRepositorio.save( this );
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais.getNome();
    }

}
