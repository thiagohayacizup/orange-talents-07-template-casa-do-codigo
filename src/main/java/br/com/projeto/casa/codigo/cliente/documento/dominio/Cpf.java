package br.com.projeto.casa.codigo.cliente.documento.dominio;

public class Cpf {

    private static final Integer[] PESOS_DIGITO_UM = {10,9,8,7,6,5,4,3,2};
    private static final Integer[] PESOS_DIGITO_DOIS = {11,10,9,8,7,6,5,4,3,2};

    private final String cpf;

    private boolean valido;

    private Cpf(final String cpf) {
        this.cpf = cpf;
        valido = true;
    }

    public static Cpf de( final String cpf ){
        return new Cpf( cpf );
    }

    public Cpf validarPrimeiroDigito(){
        int somatoria = 0;
        for( int contador = 0; contador < PESOS_DIGITO_UM.length; contador++ ){
            somatoria += Character.getNumericValue(cpf.charAt( contador )) * PESOS_DIGITO_UM[ contador ];
        }
        int modulo = ( somatoria * 10 ) % 11;
        if( modulo == 10 ) modulo = 0;
        if( Character.getNumericValue( cpf.charAt( 9 ) ) != modulo ) valido = false;
        return this;
    }

    public Cpf validarSegundoDigito(){
        int somatoria = 0;
        for( int contador = 0; contador < PESOS_DIGITO_DOIS.length; contador++ ){
            somatoria += Character.getNumericValue(cpf.charAt( contador )) * PESOS_DIGITO_DOIS[ contador ];
        }
        int modulo = ( somatoria * 10 ) % 11;
        if( modulo ==10 ) modulo = 0;
        if( Character.getNumericValue( cpf.charAt( 10 ) ) != modulo ) valido = false;
        return this;
    }

    public boolean eValido(){
        return valido;
    }

}
