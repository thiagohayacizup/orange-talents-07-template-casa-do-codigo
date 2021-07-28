package br.com.projeto.casa.codigo.categoria.modelo.excessao;

public class CategoriaJaCadastradaException extends RuntimeException{

    public CategoriaJaCadastradaException(final String mensagem) {
        super(mensagem);
    }

}
