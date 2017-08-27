package com.zieg.petcare.constraint.fornecedores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FornecedorValidator.class})
@Documented
public @interface FornecedorConstraint {

	String message() default "{com.zieg.petcare.constraint.fornecedores.FornecedorConstraint.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
	
}
