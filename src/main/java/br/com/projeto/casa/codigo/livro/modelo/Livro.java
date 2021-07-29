package br.com.projeto.casa.codigo.livro.modelo;

import br.com.projeto.casa.codigo.autor.modelo.Autor;
import br.com.projeto.casa.codigo.categoria.modelo.Categoria;
import br.com.projeto.casa.codigo.livro.repositorio.LivroProjecao;
import br.com.projeto.casa.codigo.livro.repositorio.LivroRepositorio;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Livro {

    public static Builder builder(){
        return new Builder();
    }

    public static List<LivroProjecao> buscarLivros( final LivroRepositorio livroRepositorio ){
        return livroRepositorio.buscarLivros();
    }

    private Livro(){}

    public Livro( final Builder builder ){
        this.titulo = builder.titulo;
        this.resumo = builder.resumo;
        this.sumario = builder.sumario;
        this.preco = builder.preco;
        this.numeroPaginas = builder.numeroPaginas;
        this.isbn = builder.isbn;
        this.dataLancamento = builder.dataLancamento;
        this.categoria = builder.categoria;
        this.autor = builder.autor;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size( max = 500 )
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull
    @DecimalMin( value = "20.0", message = "Preco deve ser no minimo 20.0" )
    private BigDecimal preco;

    @NotNull
    @Positive
    @Min( value = 100, message = "Numero de paginas deve ser no minimo 100")
    private Integer numeroPaginas;

    @NotBlank
    private String isbn;

    @NotNull
    @Future
    private LocalDate dataLancamento;

    @OneToOne
    @NotNull
    private Categoria categoria;

    @ManyToOne( fetch = FetchType.LAZY )
    @NotNull
    private Autor autor;

    public Livro cadastrar(final LivroRepositorio livroRepositorio){
        return livroRepositorio.save( this );
    }

    public static class Builder {

        private String titulo;
        private String resumo;
        private String sumario;
        private BigDecimal preco;
        private Integer numeroPaginas;
        private String isbn;
        private LocalDate dataLancamento;
        private Categoria categoria;
        private Autor autor;

        private Builder(){}

        public Builder comAutor( final Autor autor ){
            this.autor = autor;
            return this;
        }

        public Builder comCategoria( final Categoria categoria ){
            this.categoria = categoria;
            return this;
        }

        public Builder comDataLancamento( final LocalDate dataLancamento ){
            this.dataLancamento = dataLancamento;
            return this;
        }

        public Builder comIsbn( final String isbn ){
            this.isbn = isbn;
            return this;
        }

        public Builder comNumeroPaginas( final Integer numeroPaginas ){
            this.numeroPaginas = numeroPaginas;
            return this;
        }

        public Builder comPreco( final BigDecimal preco ){
            this.preco = preco;
            return this;
        }

        public Builder comSumario( final String sumario ){
            this.sumario = sumario;
            return this;
        }

        public Builder comResumo( final String resumo ){
            this.resumo = resumo;
            return this;
        }

        public Builder comTitulo( final String titulo ){
            this.titulo = titulo;
            return this;
        }

        public Livro construir(){
            return new Livro( this );
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

}
