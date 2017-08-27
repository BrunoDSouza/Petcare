package com.zieg.petcare.model.bean;

public class ItemMovimentacao {
	
	private String tipo;
	private String setor;
	private Long idsetor;
	private String produto;
	private Long idproduto;
	private Long qtd_produto;
	
	//Getters and Setters
	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }
	public String getSetor() { return setor; }
	public void setSetor(String setor) { this.setor = setor; }
	public Long getIdsetor() { return idsetor; }
	public void setIdsetor(Long idsetor) { this.idsetor = idsetor; }
	public String getProduto() { return produto; }
	public void setProduto(String produto) { this.produto = produto; }
	public Long getIdproduto() { return idproduto; }
	public void setIdproduto(Long idproduto) { this.idproduto = idproduto; }
	public Long getQtd_produto() { return qtd_produto; }
	public void setQtd_produto(Long qtd_produto) { this.qtd_produto = qtd_produto; }
		
}
