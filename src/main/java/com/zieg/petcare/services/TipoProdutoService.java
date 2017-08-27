package com.zieg.petcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zieg.petcare.repository.TiposProdutosRepository;
import com.zieg.petcare.model.TipoProduto;
import com.zieg.petcare.model.enums.TipoStatus;

@Service
public class TipoProdutoService {

	@Autowired
	TiposProdutosRepository TipoRepository;
	
	public List<TipoProduto> findAll(){
		return TipoRepository.findAll();
	}
	
	public List<TipoProduto> findByStatus(TipoStatus tipo){
		return TipoRepository.findByStatus(tipo);
	}
	
	
	public TipoProduto findOne(Long codigo){
		return TipoRepository.findOne(codigo);
	}
	
	public TipoProduto findByName(String name){
		return TipoRepository.findByTipoIgnoreCase(name);
	}
	
	public TipoProduto findByNameAndCodigo(String name, Long codigo){
		return TipoRepository.findByTipoIgnoreCaseAndCodigoNot(name, codigo);
	}
	
	
	public void save(TipoProduto tipo){
		TipoRepository.save(tipo);
	}
	
	public void delete(Long codigo){
		TipoRepository.delete(codigo);
	}
}
