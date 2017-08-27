package com.zieg.petcare.services;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zieg.petcare.model.Movimentacao;
import com.zieg.petcare.model.Produto;
import com.zieg.petcare.model.bean.ItemProduto;
import com.zieg.petcare.model.enums.TipoMovimentacao;
import com.zieg.petcare.repository.MovimentacoesRepository;
import com.zieg.petcare.repository.ProdutosRepository;


@Service
public class ProdutoService {

	@Autowired MovimentacoesRepository m;
	@Autowired ProdutosRepository produtoRepository;
	
	
	public Produto findOne(Long codigo){		
		return produtoRepository.findOne(codigo);
	}
	
	public Produto findByName(String name){
		return produtoRepository.findByNomeIgnoreCase(name);
	}
	
	public Produto findByNameAndCodigo(String name, Long codigo){
		return produtoRepository.findByNomeIgnoreCaseAndCodigoNot(name, codigo);
	}
	
	public List<Produto> findAll(){
		
		return produtoRepository.findAll()
				.stream()
				.map(p -> {
					p.setQtd_estoque(getTotal(p));
					p.setEstoque_min(getEstoqueMin(p)); 
					return p;
				})
				.collect(Collectors.toList());
								 
	}
	
	public void save(Produto produto){
		produtoRepository.save(produto);
	}
	
	public void delete(Long codigo){
		produtoRepository.delete(codigo);
	}
	
	public Long getTotal(Produto produto){
		
		Long entrada = m.findByProduto(produto)
						.stream()
						.filter(mv -> mv.getTipoMovimentacao()
										.equals(TipoMovimentacao.ENTRADA))
						.mapToLong(Movimentacao::getQtd_produto)
						.sum();
		
		Long saida = m.findByProduto(produto)
					  .stream()
					  .filter(mv -> !mv.getTipoMovimentacao()
							  		   .equals(TipoMovimentacao.ENTRADA))
					  .mapToLong(Movimentacao::getQtd_produto)
					  .sum();
		
		return entrada - saida;
		
	}
	
	public Long getTotal(){
		Long entrada = m.findByTipoMovimentacao(TipoMovimentacao.ENTRADA)
						.stream()
						.mapToLong(Movimentacao::getQtd_produto)
						.sum();

		Long saida = m.findByTipoMovimentacaoNot(TipoMovimentacao.ENTRADA)
					  .stream()
					  .mapToLong(Movimentacao::getQtd_produto)
					  .sum();
		
		return entrada - saida;
		
	}
	
	public Long getTotalEstoqueOff(){
		
		return findAll()
			   .stream()
			   .filter(p -> p.getQtd_estoque() <= p.getEstoque_min())
			   .count();
		
	}
	
	public List<Produto> findProdutosEstoqueOff(){
		
		return findAll()
			   .stream()
			   .filter(p -> p.getQtd_estoque() <= p.getEstoque_min())
			   .collect(Collectors.toList());
	
	}
	
	public Long getEstoqueMin(Produto produto){
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		
		return m.findByProdutoAndDtMovimentacaoGreaterThanEqual(produto,c.getTime())
				.stream()
				.filter(m -> !m.getTipoMovimentacao()
							   .equals(TipoMovimentacao.ENTRADA))
				.mapToLong(Movimentacao::getQtd_produto)
				.sum() / 3;
		
	}
	
	public List<ItemProduto> getPercentualProdutos(TipoMovimentacao Tipo){
		
		Long total = m.findByTipoMovimentacao(Tipo)
					  .stream()
					  .collect(Collectors.summingLong(Movimentacao::getQtd_produto));
		
		return  m.findByTipoMovimentacao(Tipo)
				.stream()
			    .collect(Collectors.groupingBy(Movimentacao::getProduto, 
					     Collectors.summingLong(Movimentacao::getQtd_produto)))
			    .entrySet().stream()
			    .map( map -> {
			     	  map.getKey().setPercentual(
							  	getPercentual(map.getValue(), total)
							  );
			     	  return convertToItemsProduto(map.getKey());
		 	    })
			    .sorted(Comparator.comparing(ItemProduto::getPercentual)
			    		          .reversed())
			    .limit(10)
			    .collect(Collectors.toList());

	}
	
	public double getPercentual(double valor, double total ){
		
		return Math.round(((valor * 100) * 100) / total) /100D;
		
	}
	
	public ItemProduto convertToItemsProduto(Produto produto){
		
		return new ItemProduto(produto.getNome(), produto.getPercentual());
	
	}
	
	
}
