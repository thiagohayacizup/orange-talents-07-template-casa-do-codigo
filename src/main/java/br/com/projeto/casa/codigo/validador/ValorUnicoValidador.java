package br.com.projeto.casa.codigo.validador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ValorUnicoValidador implements ConstraintValidator<ValorUnico, Object> {

    private String atributo;

    private Class<?> classe;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        atributo = constraintAnnotation.campo();
        classe = constraintAnnotation.dominio();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        final Query query = entityManager.createQuery(
                "SELECT 1 FROM " + classe.getName() + " WHERE " + atributo + " = :value"
        );
        query.setParameter("value", o );
        return query.getResultList().isEmpty();
    }

}














