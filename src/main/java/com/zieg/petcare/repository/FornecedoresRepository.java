package com.zieg.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zieg.petcare.model.Fornecedor;


public interface FornecedoresRepository extends JpaRepository<Fornecedor, Long> {

	public Fornecedor findByNomeIgnoreCase(String name);
	public Fornecedor findByNomeIgnoreCaseAndCodigoNot(String name, Long codigo);
	
	public Fornecedor findByCnpj(String cnpj);
	public Fornecedor findByCnpjAndCodigoNot(String cnpj, Long codigo);
	
}
