package com.zieg.petcare.model;

import java.util.ArrayList;
import java.util.List;

//import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.zieg.petcare.constraint.Setores.SetorConstraint;
import com.zieg.petcare.model.enums.TipoStatus;

@Entity
@Table(name="setor")
@SetorConstraint
public class Setor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idsetor")
	private Long idSetor;
	
	@NotBlank
	@Column(name="setor")
	@Size(message="O nome do setor deve possuir no máximo 100 caracteres!", max = 100)
	private String descricao;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	private TipoStatus status;
	
	
	@OneToMany(mappedBy="setor")
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

	/*Getters and Setters*/
	public Long getIdSetor() { return idSetor; }
	public void setIdSetor(Long idSetor) { this.idSetor = idSetor; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public TipoStatus getStatus() { return status; }
	public void setStatus(TipoStatus status) { this.status = status;}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSetor == null) ? 0 : idSetor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setor other = (Setor) obj;
		if (idSetor == null) {
			if (other.idSetor != null)
				return false;
		} else if (!idSetor.equals(other.idSetor))
			return false;
		return true;
	}
	
	

	
}
