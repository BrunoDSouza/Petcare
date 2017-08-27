package com.zieg.petcare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zieg.petcare.model.Setor;
import com.zieg.petcare.model.TipoProduto;
import com.zieg.petcare.model.Fornecedor;
import com.zieg.petcare.model.Movimentacao;
import com.zieg.petcare.model.bean.ItemData;
import com.zieg.petcare.model.bean.ItemDataCollection;
import com.zieg.petcare.model.bean.ItemMovimentacao;
import com.zieg.petcare.model.bean.ItemProduto;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.model.enums.TipoMovimentacao;
import com.zieg.petcare.services.FornecedorService;
import com.zieg.petcare.services.MovimentacaoService;
import com.zieg.petcare.services.ProdutoService;
import com.zieg.petcare.services.SetoresService;
import com.zieg.petcare.services.TipoProdutoService;

/**
 * @author Bruno D. Souza
 *
 * @RestController Notacao utilizada na declaracao da class para mostrar
 * 				   ao Spring que o return dos metodos serão o corpo de resposta
 * 				   de uma request, evitando de buscar uma view.
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired MovimentacaoService MovimentacaoService;
	@Autowired ProdutoService ProdutoService;
	@Autowired FornecedorService FornecedorService;
	@Autowired SetoresService SetorService;
	@Autowired TipoProdutoService TiposService;
	
	@GetMapping("/listProdutos")
	public ModelAndView ListProduto(){
	
	  ModelAndView mv = new ModelAndView("movimentacao/cadastro-movimentacao :: ListProdutos");	
	  mv.addObject("produtos", ProdutoService.findAll());	  
	  return mv;
	  
	}
	
	@GetMapping("/listProdutosAll")
	public ModelAndView ListProdutoAll(){
	
	  ModelAndView mv = new ModelAndView("home/dashboard :: ListProdutosAll");	
	  mv.addObject("produtos", ProdutoService.findAll());	  
	  return mv;
	  
	}
	
	@GetMapping("/listProdutosOff")
	public ModelAndView ListProdutoOff(){
	
	  ModelAndView mv = new ModelAndView("home/dashboard :: ListProdutosOff");	
	  mv.addObject("produtos", ProdutoService.findProdutosEstoqueOff());
	  return mv;
	  
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/edit/setor/{codigo}")
	public ModelAndView getSetor(@PathVariable Long codigo){
		
		ModelAndView mv = new ModelAndView("setor/lista-setor :: modalSetor");
		mv.addObject(SetorService.findOne(codigo));
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/novo/setor")
	public ModelAndView newSetor(){
		ModelAndView mv = new ModelAndView("setor/lista-setor :: modalSetor");
		mv.addObject("setor", new Setor());
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/edit/tipo/{codigo}")
	public ModelAndView getTipoProduto(@PathVariable Long codigo){
		
		ModelAndView mv = new ModelAndView("tipoproduto/lista-tipos :: modalTipo");
		mv.addObject("tipo",TiposService.findOne(codigo));
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/novo/tipo")
	public ModelAndView newTipo(){
		ModelAndView mv = new ModelAndView("tipoproduto/lista-tipos :: modalTipo");
		mv.addObject("tipo", new TipoProduto());
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/edit/fornecedor/{codigo}")
	public ModelAndView getFornecedor(@PathVariable Long codigo){
		
		ModelAndView mv = new ModelAndView("fornecedor/lista-fornecedor :: modalFornecedor");
		mv.addObject("fornecedor",FornecedorService.findOne(codigo));
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
		
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/novo/fornecedor")
	public ModelAndView newFornecedor(){
		ModelAndView mv = new ModelAndView("fornecedor/lista-fornecedor :: modalFornecedor");
		mv.addObject("fornecedor", new Fornecedor());
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
	}
	
	
	
	@PreAuthorize("hasAnyRole('ADMIN', 'FUNC')")
	@GetMapping("/addProduto/{codigo}")
	public ModelAndView addProduto(@PathVariable Long codigo){
	
	  ModelAndView mv = new ModelAndView("movimentacao/cadastro-movimentacao :: addProduto");	
	  mv.addObject("produto", ProdutoService.findOne(codigo));
	  
	  return mv;
	  
	  
	}
	
	/**
	 * @ModelAttribute Notacao responsavel por permitir a estrutura de chave/valor ao 
	 * 				   add valores no responseHeader.
	 * 
	 * @ResquestBody Notacao responsável por mostrar ao spring que o valor da variavel
	 * 				 virá de um corpo da request. Atribute "data:"
	 * 
	 * */
	
	@PreAuthorize("hasAnyRole('ADMIN', 'FUNC')")
	@PostMapping("/listaItens")
	@ModelAttribute 
	public ModelAndView listaItens(@RequestBody String items,
									HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
	  
	  /**Converter String JSON no objeto requerido*/
	  ObjectMapper mapper = new ObjectMapper();
	  List<ItemMovimentacao> itemsMovimentacao = mapper.readValue(items, TypeFactory.defaultInstance()
				  									   .constructCollectionType(List.class, ItemMovimentacao.class));

	  /**Valida quantidade de itens entrados para a movimentacao*/	  
	  boolean validation = MovimentacaoService.validateItem(itemsMovimentacao);
	  
	  
	  /**Add result in responseHeader request*/ 
	  response.setHeader("valid", String.valueOf(validation));
	  List<Movimentacao> itemsList = MovimentacaoService.getMovimentacaoCollection(itemsMovimentacao);
	  double total = MovimentacaoService.getTotalMovimentacao(itemsList);
	  
	  /**Retorna os dados do model para um th:fragment na view*/
	  ModelAndView mv = new ModelAndView("movimentacao/cadastro-movimentacao :: itensAdicionados");	
	  mv.addObject("itemsList", itemsList);
	  mv.addObject("total",total);
	  
	  return mv;
	  
	}
	
	
	@GetMapping("/chartProdutos/saida")
	public String chartProdutosSaida() throws JsonProcessingException{
		
		List<ItemProduto> produtos = ProdutoService.getPercentualProdutos(TipoMovimentacao.SAIDA);
		ObjectMapper mapper = new ObjectMapper();
		
		String  dataJSON =  mapper.writeValueAsString(produtos);
		
		return dataJSON;
	}
	
	
	@GetMapping("/chartProdutos/entrada")
	public String chartProdutosEntrada() throws JsonProcessingException{
		
		List<ItemProduto> produtos = ProdutoService.getPercentualProdutos(TipoMovimentacao.ENTRADA);
		ObjectMapper mapper = new ObjectMapper();
		
		String  dataJSON =  mapper.writeValueAsString(produtos);
		
		return dataJSON;
	}
	
	@GetMapping("/chartFaturamento")
	public String chartFaturamento() throws JsonProcessingException{
		
		List<ItemData> items = MovimentacaoService.getFaturamentomensal();
		ObjectMapper mapper = new ObjectMapper();
		
		String dataJSON =  mapper.writeValueAsString(items);
		
		return dataJSON;
	}
	
	@GetMapping("/chartFornecedor")
	public String chartFornecedor() throws JsonProcessingException{
		
		List<ItemDataCollection> items = MovimentacaoService.getFaturamentoFornecedor();
		ObjectMapper mapper = new ObjectMapper();
		
		String dataJSON =  mapper.writeValueAsString(items);
		
		return dataJSON;
	}
	
}
