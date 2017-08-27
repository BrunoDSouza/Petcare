package com.zieg.petcare.constraint.Setores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zieg.petcare.model.Setor;
import com.zieg.petcare.services.SetoresService;

public class SetorValidator implements ConstraintValidator<SetorConstraint, Setor> {

	@Autowired
	SetoresService SetorService;
	
	@Override
	public void initialize(SetorConstraint constraintAnnotation) {}

	@Override
	public boolean isValid(Setor setor, ConstraintValidatorContext context) {
		
		String name = setor.getDescricao();
		Long codigo = setor.getIdSetor();
		
		return duplicateName(name, codigo, context);
		
	}
	
	public boolean duplicateName(String name, Long codigo, ConstraintValidatorContext context){
		
		Setor setor = codigo != null ? SetorService.findByNameAndCodigo(name, codigo)
									 : SetorService.findByName(name);
		
		if(setor != null){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Já existe um Setor com esse nome, por favor tente outro!")
				   .addPropertyNode("descricao").addConstraintViolation();
			
			return false;
		}
		
		return true;
		
	}

}
