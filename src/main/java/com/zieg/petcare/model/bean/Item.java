package com.zieg.petcare.model.bean;

public class Item {

	public Item(){};
	public Item(String json){
		this.valor = json;
	}
	
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
}
