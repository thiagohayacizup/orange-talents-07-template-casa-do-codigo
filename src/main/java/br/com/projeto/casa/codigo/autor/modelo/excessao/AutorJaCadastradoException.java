package br.com.projeto.casa.codigo.autor.modelo.excessao;

public class AutorJaCadastradoException extends RuntimeException {

    public AutorJaCadastradoException(final String mensagem) {
        super(mensagem);
    }

}
