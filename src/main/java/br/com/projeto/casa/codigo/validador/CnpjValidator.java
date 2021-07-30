package br.com.projeto.casa.codigo.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<Cnpj, String> {

    @Override
    public boolean isValid(final String s, final ConstraintValidatorContext constraintValidatorContext) {
        final String cnpj = s.replaceAll("\\.", "")
                .replaceAll("-", "")
                .replaceAll("/","");
        System.out.println(cnpj + cnpj.length());
        if( cnpj.length() != 14 ) return false;
        return br.com.projeto.casa.codigo.cliente.documento.dominio.Cnpj
                .de( cnpj )
                .validarPrimeiroDigito()
                .validarSegundoDigito()
                .eValido();
    }

}
