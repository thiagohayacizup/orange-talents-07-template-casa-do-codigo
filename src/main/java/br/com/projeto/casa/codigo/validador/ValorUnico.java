package br.com.projeto.casa.codigo.validador;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint( validatedBy = {ValorUnicoValidador.class} )
@Target( {ElementType.FIELD, ElementType.PARAMETER} )
@Retention( RetentionPolicy.RUNTIME )
public @interface ValorUnico {

    String message() default "Valor deve ser unico";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String campo();

    Class<?> dominio();

}
