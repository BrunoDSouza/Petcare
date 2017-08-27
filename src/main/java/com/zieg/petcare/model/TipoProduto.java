package com.zieg.petcare.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotBlank;

import com.zieg.petcare.constraint.TipoProduto.TipoProdutoConstraint;
import com.zieg.petcare.model.enums.TipoStatus;

/**
 * @author Bruno D. Souza
 *
 */
@Entity
@Table(name="tipo_produto")
@TipoProdutoConstraint
public class TipoProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo")
	private Long codigo;
	
	@NotBlank
	@Column(name="tipo")
	@Size(message="O tipo deve conter no máximo 50 caracteres!", max = 50)
	private String tipo;
	
	@Column(name="descricao")
	@Size(message="A sua descrição deve conter no máximo 255 caracteres!", max = 255)
	private String descricao;
	
	@NotNull
	@Column(name="cod_status")
	private TipoStatus status;
	
	@OneToMany(mappedBy="tipoproduto", orphanRemoval=false)
	@Cascade(CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	
		
	//Getters and Setters
	
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) {this.codigo = codigo;}
	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public TipoStatus getStatus() { return status; }
	public void setStatus(TipoStatus status) { this.status = status; }
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		TipoProduto other = (TipoProduto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	


	
}
