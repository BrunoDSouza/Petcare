package com.zieg.petcare.model.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemData {

	public ItemData(String chave, double valor ){
		this.chave = chave;
		this.valor = valor;
	}
	
	@JsonProperty(value="name", required=true)
	private String chave;
	@JsonProperty(value="data", required=true)
	private double valor;
	
	
	/*@Getters and @Setters*/
	public String getChave() { return chave; } 
	public void setChave(String chave) { this.chave = chave; }
	public double getValor() { return valor; }
	public void setValor(double valor) { this.valor = valor; }
	
	
	
}
