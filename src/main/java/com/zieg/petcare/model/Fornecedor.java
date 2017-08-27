package com.zieg.petcare.model;

import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.NumberFormat;

import com.zieg.petcare.constraint.fornecedores.FornecedorConstraint;
import com.zieg.petcare.model.enums.TipoStatus;

/**
 * @author Bruno D. Souza
 *
 */
@Entity
@Table(name="fornecedor")
@FornecedorConstraint
public class Fornecedor {
	
	@Id
	@Column(name="idfornecedor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank
	@Column(name="nome")
	@Size(message="O nome deve possuir entre 6 e 100 caracteres!", max = 100, min = 6)
	private String nome;
	
	@Column(name="cnpj")
	@NumberFormat(pattern = "##.###.###/####-##00.000.000/0000-00")
	@CNPJ(message="CNPJ é inválido!")
	private String cnpj;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TipoStatus cod_status = TipoStatus.ATIVADO;

	@OneToMany(mappedBy="fornecedor", orphanRemoval=false)
	@Cascade(CascadeType.ALL)
	private List<Produto> produtos = new ArrayList<Produto>();
		
	/*Getters and Setters*/

	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }
	public TipoStatus getCod_status() { return cod_status; }
	public void setCod_status(TipoStatus cod_status) { this.cod_status = cod_status; }
	
	
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
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
		
	
}
