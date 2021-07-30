package br.com.projeto.casa.codigo.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(final String o, final ConstraintValidatorContext constraintValidatorContext) {
        final String cpf = o.replaceAll("\\.", "").replaceAll("-", "");
        if( cpf.length() != 11) return false;
        return br.com.projeto.casa.codigo.cliente.documento.dominio.Cpf
                .de( cpf )
                .validarPrimeiroDigito()
                .validarSegundoDigito()
                .eValido();
    }

}
