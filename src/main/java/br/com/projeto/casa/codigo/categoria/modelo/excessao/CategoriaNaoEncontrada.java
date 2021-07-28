package br.com.projeto.casa.codigo.categoria.modelo.excessao;

public class CategoriaNaoEncontrada extends RuntimeException {

    public CategoriaNaoEncontrada(final String mensagem){
        super( mensagem );
    }

}
