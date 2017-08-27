package com.zieg.petcare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zieg.petcare.model.Setor;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.repository.SetoresRepository;

@Service
public class SetoresService {

	@Autowired SetoresRepository SetorRepository;
	
	
	public Setor findOne(Long codigo){
		return SetorRepository.findOne(codigo);
	}
	
	public Setor findByName(String name){
		return SetorRepository.findByDescricaoIgnoreCase(name);
	}
	
	public Setor findByNameAndCodigo(String name, Long codigo){
		return SetorRepository.findByDescricaoIgnoreCaseAndIdSetorNot(name, codigo);
	}
	
	public List<Setor> findAll(){
		return SetorRepository.findAll();
	}
	
	public List<Setor> findSetorActive(){
		return SetorRepository.findByStatusNot(TipoStatus.DESATIVADO);
	}
	
	public void save(Setor setor){
		SetorRepository.save(setor);
	}
	
	
	
}
