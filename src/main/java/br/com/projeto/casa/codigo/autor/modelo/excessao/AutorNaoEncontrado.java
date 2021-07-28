package br.com.projeto.casa.codigo.autor.modelo.excessao;

public class AutorNaoEncontrado extends RuntimeException {

    public AutorNaoEncontrado(final String mensagem){
        super( mensagem );
    }

}
