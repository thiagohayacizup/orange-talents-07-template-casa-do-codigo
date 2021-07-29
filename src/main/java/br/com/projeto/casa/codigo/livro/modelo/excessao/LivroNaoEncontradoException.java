package br.com.projeto.casa.codigo.livro.modelo.excessao;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException( final String mensagem ){
        super( mensagem );
    }

}
