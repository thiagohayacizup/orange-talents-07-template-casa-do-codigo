package br.com.projeto.casa.codigo.notificacao;

class RespostaErro {

    private final Integer codigo;

    private final String mensagem;

    RespostaErro(final Integer codigo, final String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public Integer getField() {
        return codigo;
    }

    public String getMessage() {
        return mensagem;
    }

}
