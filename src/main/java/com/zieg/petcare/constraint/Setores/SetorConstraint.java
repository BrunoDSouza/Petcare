package com.zieg.petcare.constraint.Setores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SetorValidator.class})
@Documented
public @interface SetorConstraint {

	String message() default "{com.zieg.petcare.constraint.Setores.SetorConstraint.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
	
}
