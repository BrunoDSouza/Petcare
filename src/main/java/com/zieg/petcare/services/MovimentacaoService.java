package com.zieg.petcare.services;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zieg.petcare.model.Movimentacao;
import com.zieg.petcare.model.Produto;
import com.zieg.petcare.model.bean.ItemData;
import com.zieg.petcare.model.bean.ItemDataCollection;
import com.zieg.petcare.model.bean.ItemMovimentacao;
import com.zieg.petcare.model.enums.TipoMovimentacao;
import com.zieg.petcare.repository.MovimentacoesRepository;
import com.zieg.petcare.repository.ProdutosRepository;

import com.zieg.petcare.repository.SetoresRepository;

@Service
public class MovimentacaoService {

	//Injeção de Repositórios e Services
	@Autowired private SetoresRepository s;
	@Autowired private ProdutosRepository p;
	@Autowired private MovimentacoesRepository m;	
	@Autowired private ProdutoService produtoService;
	@Autowired private UtilsService utils;
	
	private final Calendar calendar = Calendar.getInstance();
	 
	public void salvar(List<ItemMovimentacao> listItens ){
		
		try{
			
			List<Movimentacao> mv = listItens
									.stream()
									.map(l -> getMovimentacao(l))
									.collect(Collectors.toList());	

			m.save(mv);
			
		}catch(Exception e) {
			throw new RuntimeException(e.toString());
		}

	}

	public Movimentacao getMovimentacao(ItemMovimentacao item){
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Movimentacao mv = new Movimentacao();	
		Produto produto = p.findOne(item.getIdproduto());
		
		double valor = TipoMovimentacao.valueOf(item.getTipo()) == TipoMovimentacao.SAIDA ? produto.getVl_venda()
																						    : produto.getVl_compra();
		
		mv.setProduto(produto);
		mv.setSetor(s.findOne(item.getIdsetor()));
		mv.setTipoMovimentacao(TipoMovimentacao.valueOf(item.getTipo()));
		mv.setQtd_produto(item.getQtd_produto());
		mv.setVl_unitario(valor);
		mv.setUsuario(username);

		return mv;
		
	}
	
	public List<Movimentacao> getMovimentacaoCollection(List<ItemMovimentacao> items){
		
		return items
			   .stream()
			   .map(l -> getMovimentacao(l))
			   .collect(Collectors.toList());
	}
	
	public double getTotalMovimentacao(List<Movimentacao> movimentacoes){
		
		return movimentacoes
		  	   .stream()
		  	   .mapToDouble(l -> l.getVl_unitario() * l.getQtd_produto())
		  	   .sum();
	}
	
	
	public boolean validateItem(List<ItemMovimentacao> listItens){
		
		Movimentacao item = listItens.isEmpty() ? null : listItens.stream()
				   					 							  .reduce((first, last) -> last)
				   					 							  .map(l -> getMovimentacao(l)).get();
		
		if(item != null){
			if (!item.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA)) {
				Produto produto = item.getProduto();
				Long qtd = item.getQtd_produto();
				
				Long total = produtoService.getTotal(produto);
				
				if(qtd > total){ 
					listItens.remove(listItens.size()-1);
					return false;
				}
					
			}
		}

		return true;
	}
	
	public Double getFaturamento(TipoMovimentacao tipo){
		
		return m.findByTipoMovimentacao(tipo)
				.stream()
				.mapToDouble(mv -> mv.getValorMovimentacao(mv.getVl_unitario()))
				.sum();
		
	}
	
	public Double getFaturamento(Integer key, TipoMovimentacao tipo){
			
		return m.findByTipoMovimentacao(tipo)
				.stream()
				.filter(mv -> {
					Calendar c = Calendar.getInstance();
					c.setTime(mv.getDtMovimentacao());
					if(c.get(key) >= calendar.get(key))
						return true;
					
					return false;
				})
				.mapToDouble(mv -> {
					Double vlItem = mv.getVl_unitario();
					return mv.getValorMovimentacao(vlItem);
				})
				.sum();
		
	}
	
	public Double getFaturaLiquido(Integer key, TipoMovimentacao tipo){
		
		return m.findByTipoMovimentacao(tipo)
				.stream()
				.filter(mv -> {
					Calendar c = Calendar.getInstance();
					c.setTime(mv.getDtMovimentacao());
					if(c.get(key) >= calendar.get(key))
						return true;
					
					return false;
				})
				.mapToDouble(mv -> { 
					return mv.getValorMovimentacao();
				})
				.sum();
		
	}
	
	public List<ItemData> getFaturamentomensal(){
		
		Date date = utils.getDate(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1);
		
		return m.findByTipoMovimentacaoAndDtMovimentacaoGreaterThanEqual(TipoMovimentacao.SAIDA, date)
				.stream()
				.collect(Collectors.groupingBy(utils.getNameMonth,
						 Collectors.summingDouble(Movimentacao::getValorMovimentacao)))
				.entrySet().stream()
				.map(entry -> {
					String key = entry.getKey();
					double value = entry.getValue();
					return new ItemData(key, value);
				 })
				.sorted(Comparator.comparing(item -> utils.getNumberMonth.apply(item.getChave())))
				.collect(Collectors.toList());
		
	}
	
	public List<ItemDataCollection> getFaturamentoFornecedor(){
		
		Date date = utils.getDate(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1);
		
		return m.findByTipoMovimentacaoAndDtMovimentacaoGreaterThanEqual(TipoMovimentacao.SAIDA, date)
			    .stream()
			    .collect(Collectors.groupingBy(utils.getNameMonth))
			    .entrySet().stream()
			    .map(entry -> {
			    	 String key = entry.getKey();
			    	 List<ItemData> items = entry.getValue()
			    			 					 .stream()
			    			 					 .collect(Collectors.groupingBy(utils.getNameFornecedor,
			    			 							  Collectors.summingDouble(Movimentacao::getValorMovimentacao)))
			    			 					 .entrySet().stream()
			    			 					 .map(value -> {
			    			 						String keyItem = value.getKey();
			    			 						double valueItem = value.getValue();
			    			 						return new ItemData(keyItem, valueItem);
			    			 					 })
			    			 					 .sorted(Comparator.comparing(ItemData::getValor))
			    			 					 .collect(Collectors.toList());
			    	 return new ItemDataCollection(key, items);
			    })
			    .sorted(Comparator.comparing(item -> utils.getNumberMonth.apply(item.getChave())))
				.collect(Collectors.toList());
	}
	

	

}
