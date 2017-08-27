package com.zieg.petcare.model;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.zieg.petcare.model.enums.TipoMovimentacao;



@Entity
@Table(name="movimentacoes")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idmovimentacao")
	private Long codigo;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name="cod_tipomovimento")
	private TipoMovimentacao tipoMovimentacao;
	
	@NotNull
	@Valid
	@JoinColumn(name="cod_produto")
	@ManyToOne
	private Produto produto;
	
	@NotNull
	@Valid
	@JoinColumn(name="cod_setor")
	@ManyToOne
	private Setor setor;
	
	@NotNull
	@Column(name="qtd_itens")
	private Long qtd_produto;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="dt_movimento")
	private Date dtMovimentacao = Calendar.getInstance().getTime();
	
	@NotBlank
	@Column(name="usuario")
	private String usuario = "Administrator";

	@Column(name="vl_unitario")
	@NotNull
	private double vl_unitario;
	
	
	
	/*Getters and Setters*/
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	public TipoMovimentacao getTipoMovimentacao() { return tipoMovimentacao; }
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
	public Produto getProduto() { return produto; }
	public void setProduto(Produto produto) { this.produto = produto; }
	public Setor getSetor() { return setor; }
	public void setSetor(Setor setor) { this.setor = setor; }
	public Long getQtd_produto() { return qtd_produto; }
	public void setQtd_produto(Long qtd_produto) { this.qtd_produto = qtd_produto; }
	public Date getDtMovimentacao() { return dtMovimentacao; }
	public void setDtMovimentacao(Date dt_movimentacao) { this.dtMovimentacao = dt_movimentacao; }
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; }
	public double getVl_unitario() { return vl_unitario; }
	public void setVl_unitario(double vl_unitario) { this.vl_unitario = vl_unitario;}
	
	/*@Override*/
	public double getValorMovimentacao(){
		
		double valor = getProduto().getVlLiquido();
		return getValorMovimentacao(valor);

	}
	
	public double getValorMovimentacao(Double valorItem){
		
		Double valor = valorItem;
		Long qtd = this.qtd_produto;
		
		return valor * qtd;
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
		Movimentacao other = (Movimentacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
	
		
}
