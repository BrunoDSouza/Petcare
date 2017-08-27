package com.zieg.petcare.constraint.fornecedores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zieg.petcare.model.Fornecedor;
import com.zieg.petcare.services.FornecedorService;
import com.zieg.petcare.services.UtilsService;

public class FornecedorValidator implements ConstraintValidator<FornecedorConstraint, Fornecedor> {

	@Autowired
	FornecedorService fornecedorService;
	
	@Autowired UtilsService utilService;
	
	@Override
	public void initialize(FornecedorConstraint constraintAnnotation) {}

	@Override
	public boolean isValid(Fornecedor fornecedor, ConstraintValidatorContext context) {
		
		String name = fornecedor.getNome();
		String cnpj = utilService.removeFormat(fornecedor.getCnpj());
		Long codigo = fornecedor.getCodigo();
		
		boolean isValidName = duplicateName(name, codigo, context);
		boolean isValidCnpj = duplicateCNPJ(cnpj, codigo, context);
		
		return (isValidName && isValidCnpj);
		
	}
	
	public boolean duplicateName(String name, Long codigo, ConstraintValidatorContext context){
		
		Fornecedor fornecedor = codigo != null ? fornecedorService.findByNameAndCodigo(name, codigo)
											   : fornecedorService.findByName(name);
		
		
		if(fornecedor != null){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Já existe um fornecedor com esse nome, por favor tente outro!")
				   .addPropertyNode("nome").addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
	
	public boolean duplicateCNPJ(String cnpj, Long codigo, ConstraintValidatorContext context){
		
		Fornecedor fornecedor = codigo != null ? fornecedorService.findByCnpjAndCodigo(cnpj, codigo)
											   : fornecedorService.findByCnpj(cnpj);
		
		
		if(fornecedor != null){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Já existe um CNPJ com esse numero, por favor tente outro!")
				   .addPropertyNode("cnpj").addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
	
	
	
	

	
	
}
