package com.zieg.petcare.model.bean;

import java.util.List;

public class ItemDataCollection {

	public ItemDataCollection(String chave, List<ItemData> values){
		this.chave = chave;
		this.valor = values;
	}
	
	private String chave;
	private List<ItemData> valor;
	
	public String getChave() { return chave; }
	public void setChave(String chave) { this.chave = chave; }
	public List<ItemData> getValor() { return valor; }
	public void setValor(List<ItemData> valor) { this.valor = valor; }
	
	
	
}
