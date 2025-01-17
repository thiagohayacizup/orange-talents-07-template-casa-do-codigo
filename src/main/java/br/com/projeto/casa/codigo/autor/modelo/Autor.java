package br.com.projeto.casa.codigo.autor.modelo;

import br.com.projeto.casa.codigo.autor.modelo.excessao.AutorNaoEncontrado;
import br.com.projeto.casa.codigo.autor.modelo.excessao.EmailFormatoInvalidoException;
import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;
import br.com.projeto.casa.codigo.email.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
public class Autor {

    public static final String NOME_INVALIDO = "Nome do autor invalido.";

    public static final String EMAIL_INVALIDO = "Email do autor invalido.";
    public static final String DESCRICAO_INVALIDO = "Descricao do autor invalido.";
    public static final String DESCRICAO_MAIS_400_CARACTERES = "Descricao tem mais de 400 caracteres.";

    public static Autor buscarPorEmail( final String email, final AutorRepositorio autorRepositorio ){
        return autorRepositorio.findByEmail( email )
                .orElseThrow(() -> new AutorNaoEncontrado(
                        String.format("Autor { %s } não encontrado.", email)
                ));
    }

    public static Autor mock( final String email ){
        return new Autor( "", email, "" );
    }

    private static boolean emailValido( final String email ){
        return Email.match( email );
    }

    Autor(){}

    public Autor(final String nome, final String email, final String descricao){
        if( !emailValido( email ) )
            throw new EmailFormatoInvalidoException(
                    String.format("Email { %s } com formato invalido.", email)
            );

        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = Instant.now();
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = NOME_INVALIDO )
    private String nome;

    @NotBlank( message = EMAIL_INVALIDO )
    private String email;

    @NotBlank( message = DESCRICAO_INVALIDO )
    @Size( max = 400, message = DESCRICAO_MAIS_400_CARACTERES)
    private String descricao;

    @NotNull
    private Instant dataCadastro;

    public Autor cadastrar( final AutorRepositorio autorRepositorio ){
        return autorRepositorio.save( this );
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
