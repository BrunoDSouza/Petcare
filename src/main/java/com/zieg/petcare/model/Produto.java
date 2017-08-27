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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import com.zieg.petcare.constraint.produtos.ProductConstraint;
import com.zieg.petcare.model.enums.TipoMedida;
import com.zieg.petcare.model.enums.TipoStatus;

/**
 * @author Bruno D. Souza
 *
 */
@Entity
@Table(name="produto")
@ProductConstraint
public class Produto {

	
	/** @Membros Privados **/
	
	@Id
	@Column(name="idproduto", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@JoinColumn(name="cod_tipo", nullable = false)
	@ManyToOne
	private TipoProduto tipoproduto;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="cod_fornecedor", nullable = false)
	private Fornecedor fornecedor;
	
	@NotBlank
	@Column(name="descricao")
	@Size(message="O tamanho do nome deve possuir no máximo 100 caracteres!", max = 100)
	private String nome;
	
	@Column(name="medida")
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TipoMedida medida;
	
	@Column(name="vl_compra")
	@NotNull
	@NumberFormat(pattern = "#,##0.00")
	@Min(message="O Valor de compra deve ser maior que R$0,00!", value = 1)
	private Double vl_compra;
	
	@Column(name="vl_venda")
	@NotNull
	@NumberFormat(pattern = "#,##0.00")
	@Min(message="O Valor de venda deve ser maior que R$0,00", value = 1)
	private Double vl_venda;
	
	@Column(name="status_produto")
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private TipoStatus status_produto = TipoStatus.ATIVADO; 
	
	/*Propriedades não mapeadas*/
	@Transient private Long qtd_estoque;
	@Transient private Long estoque_min;
	@Transient private double percentual;
	
	@OneToMany(mappedBy="produto")
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	
	/** @Getter e @Setters **/
	
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome;}
	public TipoMedida getMedida() { return medida;	}
	public void setMedida(TipoMedida medida) { this.medida = medida; }
	public Double getVl_compra() { return vl_compra; }
	public void setVl_compra(Double vl_compra) { this.vl_compra = vl_compra;}
	public Double getVl_venda() { return vl_venda; }
	public void setVl_venda(Double vl_venda) { this.vl_venda = vl_venda; }
	public TipoProduto getTipoproduto() { return tipoproduto; }
	public void setTipoproduto(TipoProduto tipoproduto) { this.tipoproduto = tipoproduto; }
	public Fornecedor getFornecedor() { return fornecedor; }
	public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
	public Long getQtd_estoque() { return qtd_estoque; }
	public void setQtd_estoque(Long qtd_estoque) { this.qtd_estoque = qtd_estoque; }	
	public Long getEstoque_min() { return estoque_min;}
	public void setEstoque_min(Long estoque_min) { this.estoque_min = estoque_min; }
	public TipoStatus getStatus_produto() { return status_produto; }
	public void setStatus_produto(TipoStatus status_produto) { this.status_produto = status_produto; }
	public double getPercentual() { return percentual; }
	public void setPercentual(double percentual) { this.percentual = percentual; }
	
	public boolean getStatus(){ 
		return !this.status_produto.equals(TipoStatus.DESATIVADO);	
	}
	
	public Double getVlLiquido(){
		return this.vl_venda - this.vl_compra;
	}
	
	public boolean isEmpty(){
		
		return ((codigo == null) && (nome == null) && (medida == null) &&
				(vl_compra == null) && (vl_venda == null) && (tipoproduto == null) &&
				(fornecedor == null) && (status_produto == null));
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
	
}
