package br.com.projeto.casa.codigo.estado.modelo.excessao;

public class EstadoNaoEncontradoException extends RuntimeException{

    public EstadoNaoEncontradoException( final String mensagem ){
        super( mensagem );
    }

}
