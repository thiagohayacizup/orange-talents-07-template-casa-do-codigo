package br.com.projeto.casa.codigo.cliente.modelo;

import br.com.projeto.casa.codigo.cliente.repositorio.ClienteRepositorio;
import br.com.projeto.casa.codigo.estado.modelo.Estado;
import br.com.projeto.casa.codigo.pais.modelo.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

    public static Builder construtor(){
        return new Builder();
    }

    private Cliente(){}

    Cliente( final Builder builder ){
        this.email = builder.email;
        this.nome = builder.nome;
        this.sobrenome = builder.sobrenome;
        this.documento = builder.documento;
        this.endereco = builder.endereco;
        this.complemento = builder.complemento;
        this.cidade = builder.cidade;
        this.pais = builder.pais;
        this.estado = builder.estado;
        this.telefone = builder.telefone;
        this.cep = builder.cep;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Column(columnDefinition = "CHAR(18)")
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @OneToOne
    private Pais pais;

    @NotNull
    @OneToOne
    private Estado estado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public Cliente cadastrar( final ClienteRepositorio clienteRepositorio ){
        return clienteRepositorio.save( this );
    }

    public static class Builder{

        private Builder(){}

        private String email;
        private String nome;
        private String sobrenome;
        private String documento;
        private String endereco;
        private String complemento;
        private String cidade;
        private Pais pais;
        private Estado estado;
        private String telefone;
        private String cep;

        public Builder comEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder comNome(final String nome) {
            this.nome = nome;
            return this;
        }

        public Builder comSobrenome(final String sobrenome) {
            this.sobrenome = sobrenome;
            return this;
        }

        public Builder comDocumento(final String documento) {
            this.documento = documento;
            return this;
        }

        public Builder comEndereco(final String endereco) {
            this.endereco = endereco;
            return this;
        }

        public Builder comComplemento(final String complemento) {
            this.complemento = complemento;
            return this;
        }

        public Builder comCidade(final String cidade) {
            this.cidade = cidade;
            return this;
        }

        public Builder comPais(final Pais pais) {
            this.pais = pais;
            return this;
        }

        public Builder comEstado(final Estado estado) {
            this.estado = estado;
            return this;
        }

        public Builder comTelefone(final String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder comCep(final String cep) {
            this.cep = cep;
            return this;
        }

        public Cliente construir(){
            return new Cliente( this );
        }

    }

    public Long getId() {
        return id;
    }

}
