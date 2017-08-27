package com.zieg.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zieg.petcare.model.Setor;
import com.zieg.petcare.model.enums.TipoStatus;

public interface SetoresRepository extends JpaRepository<Setor, Long> {

	public Setor findByDescricaoIgnoreCase(String descricao);
	public Setor findByDescricaoIgnoreCaseAndIdSetorNot(String descricao, Long codigo);
	
	public List<Setor> findByStatus(TipoStatus tipo);
	public List<Setor> findByStatusNot(TipoStatus tipo);
	
}
