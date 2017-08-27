package com.zieg.petcare.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Service;

import com.zieg.petcare.model.Fornecedor;
import com.zieg.petcare.model.Movimentacao;
import com.zieg.petcare.model.Produto;

@Service
public class UtilsService {

	private final Calendar calendar = Calendar.getInstance();
	private final Locale portuguese = new Locale("pt", "BR");
	
	
	Function<Movimentacao, String> getNameMonth = (Movimentacao m) -> {
		
		calendar.setTime(m.getDtMovimentacao());
		return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("pt", "BR"));								
												  	
	};
	
	Function<String, Integer> getNumberMonth = (String month) -> {
		
		try {
			Date date = new SimpleDateFormat("MMM", portuguese).parse(month);
			calendar.setTime(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return calendar.get(Calendar.MONTH);
		
	};
	
	Function<Movimentacao, String> getNameFornecedor = (Movimentacao m) -> {
		
		Produto produto = m.getProduto();
		Fornecedor fornecedor = produto.getFornecedor();
		return fornecedor.getNome();
		
	};
	
	public String removeFormat(String field){
		return field.replaceAll("[^a-zA-Z0-9]", "");
	}
	
	public List<Fornecedor> maskCNPJ(List<Fornecedor> fornecedores){
		
		return fornecedores
			   .stream()
			   .map(f -> {
				   String cnpj = getMaskCNPJ(f.getCnpj());
				   f.setCnpj(cnpj);
				   return f;
			   })
			   .collect(Collectors.toList());
		
	}
	
	public String getMaskCNPJ(String cnpj){
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("##.###.###/####-##");
		} 
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		formatter.setValueContainsLiteralCharacters(false);
		
		try {
			return formatter.valueToString(cnpj);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		return cnpj;
	}
	
	
	public Date getDate(int year, int month, int day){

		calendar.set(year, month, day);
		return calendar.getTime();
	}
	
	public String getNameMonth(Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, portuguese);
		
	}
	
	
}
