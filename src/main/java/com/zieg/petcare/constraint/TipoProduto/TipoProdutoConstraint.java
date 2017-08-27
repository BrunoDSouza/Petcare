package com.zieg.petcare.constraint.TipoProduto;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TipoProdutoValidator.class})
@Documented
public @interface TipoProdutoConstraint {

	String message() default "{com.zieg.petcare.constraint.TipoProduto.TipoProdutoConstraint.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
	
}
