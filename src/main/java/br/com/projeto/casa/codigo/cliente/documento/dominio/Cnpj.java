package br.com.projeto.casa.codigo.cliente.documento.dominio;

public class Cnpj {

    private static final Integer[] PESOS_DIGITO_UM = {5,4,3,2,9,8,7,6,5,4,3,2};

    private static final Integer[] PESOS_DIGITO_DOIS = {6,5,4,3,2,9,8,7,6,5,4,3,2};

    private final String cnpj;

    private boolean valido;

    private Cnpj(final String cnpj) {
        this.cnpj = cnpj;
        valido = true;
    }

    public static Cnpj de( final String cnpj ){
        return new Cnpj( cnpj );
    }

    public Cnpj validarPrimeiroDigito(){
        int somatoria = 0;
        for( int contador = 0; contador < PESOS_DIGITO_UM.length; contador++ ){
            somatoria += Character.getNumericValue(cnpj.charAt( contador )) * PESOS_DIGITO_UM[ contador ];
        }
        int modulo = ( somatoria * 10 ) % 11;
        if( modulo == 10 ) modulo = 0;
        if( Character.getNumericValue( cnpj.charAt( 12 ) ) != modulo ) valido = false;
        return this;
    }

    public Cnpj validarSegundoDigito(){
        int somatoria = 0;
        for( int contador = 0; contador < PESOS_DIGITO_DOIS.length; contador++ ){
            somatoria += Character.getNumericValue(cnpj.charAt( contador )) * PESOS_DIGITO_DOIS[ contador ];
        }
        int modulo = ( somatoria * 10 ) % 11;
        if( modulo ==10 ) modulo = 0;
        if( Character.getNumericValue( cnpj.charAt( 13 ) ) != modulo ) valido = false;
        return this;
    }

    public boolean eValido(){
        return valido;
    }

}
