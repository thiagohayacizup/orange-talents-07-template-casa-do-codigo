package br.com.projeto.casa.codigo.estado.modelo.excessao;

public class EstadoComPaisJaCadastradoException extends RuntimeException{

    public EstadoComPaisJaCadastradoException(final String mensagem){
        super( mensagem );
    }

}
