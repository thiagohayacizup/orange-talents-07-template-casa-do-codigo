package br.com.projeto.casa.codigo.pais.modelo.excessao;

public class PaisNaoEncontradoException extends RuntimeException{

    public PaisNaoEncontradoException( final String mensagem ){
        super( mensagem );
    }

}
