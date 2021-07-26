package br.com.projeto.casa.codigo.autor.modelo.excessao;

public class EmailFormatoInvalidoException extends RuntimeException {

    public EmailFormatoInvalidoException(final String format) {
        super( format );
    }

}
