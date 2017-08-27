package com.zieg.petcare.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zieg.petcare.model.Fornecedor;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.repository.FornecedoresRepository;
import com.zieg.petcare.services.UtilsService;

@Service
public class FornecedorService {

	@Autowired FornecedoresRepository fornecedorRepository;
	@Autowired UtilsService utilService;
	
	public Fornecedor findByName(String name){
		return fornecedorRepository.findByNomeIgnoreCase(name);
	}
	
	public Fornecedor findByNameAndCodigo(String name, Long codigo){
		return fornecedorRepository.findByNomeIgnoreCaseAndCodigoNot(name, codigo);
	}
	
	public Fornecedor findByCnpj(String cnpj){
		return fornecedorRepository.findByCnpj(cnpj);
	}
	
	public Fornecedor findByCnpjAndCodigo(String cnpj, Long codigo){
		return fornecedorRepository.findByCnpjAndCodigoNot(cnpj, codigo);
	}
	
	public Fornecedor findOne(Long codigo){
		return fornecedorRepository.findOne(codigo);
	}
	
	public void save(Fornecedor fornecedor){
		
		String cnpj = utilService.removeFormat(fornecedor.getCnpj());
		fornecedor.setCnpj(cnpj);
		
		fornecedorRepository.save(fornecedor);
	}
	
	public void delete(Fornecedor fornecedor){
		fornecedorRepository.delete(fornecedor);
	}
	
	public List<Fornecedor> findAll(){
		return fornecedorRepository.findAll();
	}
	
	public Long getTotal(){
		
	  return fornecedorRepository.findAll()
								 .stream()
								 .filter(f -> f.getCod_status() != TipoStatus.DESATIVADO)
								 .count();
		
	}
	
	public List<Fornecedor> getFornecedores(){
		
		return fornecedorRepository.findAll()
								   .stream()
								   .filter( f -> f.getCod_status() != TipoStatus.DESATIVADO)
								   .collect(Collectors.toList());
		
	}

	
	
}
