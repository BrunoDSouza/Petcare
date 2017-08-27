package com.zieg.petcare.model.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemProduto {

	public ItemProduto(String name, double percentual){
		this.nome = name;
		this.percentual = percentual;
	}
	
	@JsonProperty(value="name", required=true)
	private String nome;
	@JsonProperty(value="y", required=true)
	private double percentual;
	
	
	/*@Getters and @Setters*/
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public double getPercentual() { return percentual; }
	public void setPercentual(double percentual) { this.percentual = percentual; }
	
	
	
}
