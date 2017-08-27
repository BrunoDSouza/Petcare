package com.zieg.petcare.constraint.TipoProduto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zieg.petcare.model.TipoProduto;
import com.zieg.petcare.services.TipoProdutoService;

public class TipoProdutoValidator implements ConstraintValidator<TipoProdutoConstraint, TipoProduto> {

	@Autowired
	TipoProdutoService TipoService;
	
	@Override
	public void initialize(TipoProdutoConstraint constraintAnnotation) {}

	@Override
	public boolean isValid(TipoProduto tipo, ConstraintValidatorContext context) {
		
		String name = tipo.getTipo();
		Long codigo = tipo.getCodigo();
		
		return duplicateName(name, codigo, context);
		
	}
	
	public boolean duplicateName(String name, Long codigo, ConstraintValidatorContext context){
		
		TipoProduto tipo = codigo != null ? TipoService.findByNameAndCodigo(name, codigo)
										  : TipoService.findByName(name);
		
		if(tipo != null){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Já existe um Tipo de produto com esse nome, por favor tente outro!")
				   .addPropertyNode("tipo").addConstraintViolation();
			
			return false;
		}
		
		return true;
		
	}

}
