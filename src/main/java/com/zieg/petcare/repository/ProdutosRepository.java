package com.zieg.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zieg.petcare.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
	
	public Produto findByNomeIgnoreCase(String name);
	public Produto findByNomeIgnoreCaseAndCodigoNot(String name, Long codigo);
	
}
