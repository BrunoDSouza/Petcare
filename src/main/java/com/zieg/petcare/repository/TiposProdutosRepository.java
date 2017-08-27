package com.zieg.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zieg.petcare.model.enums.TipoStatus;

import com.zieg.petcare.model.TipoProduto;

public interface TiposProdutosRepository extends JpaRepository<TipoProduto, Long> {

	public TipoProduto findByTipoIgnoreCase (String name);
	public TipoProduto findByTipoIgnoreCaseAndCodigoNot(String name, Long codigo);
	public List<TipoProduto> findByStatus(TipoStatus tipo);
	
	
}
