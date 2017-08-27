package com.zieg.petcare.constraint.produtos;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zieg.petcare.model.Produto;
import com.zieg.petcare.services.ProdutoService;


public class ProductValidator implements ConstraintValidator<ProductConstraint, Produto>{
	
	@Autowired
	ProdutoService produtoService;
	
	@Override
	public void initialize(ProductConstraint constraint) {}

	@Override
	public boolean  isValid(Produto produto, ConstraintValidatorContext context) {
		
		double vlvenda = produto.getVl_venda() == null ? 0.0 : produto.getVl_venda() ;
		double vlcompra = produto.getVl_compra() == null ? 0.0 : produto.getVl_compra();
		Long codigo = produto.getCodigo();
		String nameProduto = produto.getNome();
			
		boolean valueValid = isValidValor(vlvenda, vlcompra, context);
		boolean nameValid = duplicateNameProduto(nameProduto, codigo, context);
		
		return (valueValid && nameValid);	
			
	}
	
	public boolean isValidValor(double venda, double compra, ConstraintValidatorContext context){
		
		if(venda <= compra){
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Atenção! O valor de venda deve ser maior que o valor de compra!")
				   .addPropertyNode("vl_venda").addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
	
	public boolean duplicateNameProduto(String name, Long codigo, ConstraintValidatorContext context){
		
		Produto produto = codigo != null ? produtoService.findByNameAndCodigo(name, codigo)
										 : produtoService.findByName(name);
		
		if(produto != null){
			
			context.disableDefaultConstraintViolation();;
			context.buildConstraintViolationWithTemplate("Já existe um produto com esse nome, por favor tente outro!")
				   .addPropertyNode("nome").addConstraintViolation();
			
			return false;
			
		}
		
		return true;
	
	}
	

}
