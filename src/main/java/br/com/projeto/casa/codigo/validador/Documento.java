package br.com.projeto.casa.codigo.validador;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Cpf
@Cnpj
@ConstraintComposition(CompositionType.OR)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Documento {

    String message() default "Cpf ou Cnpj invalido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
