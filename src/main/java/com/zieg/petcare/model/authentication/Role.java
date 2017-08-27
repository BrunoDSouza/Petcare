package com.zieg.petcare.model.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zieg.petcare.model.enums.TipoStatus;

@Entity
@Table(name="roles")
public class Role{

	public Role(){};
	
	public Role(Long cod, String role){
		this.codigo = cod;
		this.descricao = role;
	};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idrole")
	private Long codigo;
	
	@NotBlank
	@Column(name="descricao")
	private String descricao;
	
	@NotNull
	@Column(name="cod_status")
	private TipoStatus status = TipoStatus.ATIVADO;
	
	
	/*Getters and Setters*/
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public TipoStatus getStatus() { return status; }
	public void setStatus(TipoStatus status) { this.status = status; }
	
	

}
