package br.com.projeto.casa.codigo.categoria.modelo;

import br.com.projeto.casa.codigo.categoria.modelo.excessao.CategoriaNaoEncontrada;
import br.com.projeto.casa.codigo.categoria.repositorio.CategoriaRepositorio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    public static final String NOME_INVALIDO = "Nome da categoria invalido.";

    public static Categoria buscarPorNome( final String nome, final CategoriaRepositorio categoriaRepositorio ){
        return categoriaRepositorio.findByNome( nome )
                .orElseThrow( () -> new CategoriaNaoEncontrada(
                        String.format("Categoria { %s } não encontrada.", nome)
                ));
    }

    private Categoria(){}

    public Categoria(final String nome){
        this.nome = nome;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = NOME_INVALIDO )
    private String nome;

    public Categoria cadastrar(final CategoriaRepositorio categoriaRepositorio ){
        return categoriaRepositorio.save( this );
    }

    public String getNome() {
        return nome;
    }

}
