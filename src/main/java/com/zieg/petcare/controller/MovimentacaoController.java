package com.zieg.petcare.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zieg.petcare.model.bean.Item;
import com.zieg.petcare.model.bean.ItemMovimentacao;
import com.zieg.petcare.model.enums.TipoMedida;
import com.zieg.petcare.model.enums.TipoMovimentacao;
import com.zieg.petcare.repository.MovimentacoesRepository;
import com.zieg.petcare.repository.ProdutosRepository;
import com.zieg.petcare.repository.TiposProdutosRepository;
import com.zieg.petcare.services.FornecedorService;
import com.zieg.petcare.services.MovimentacaoService;
import com.zieg.petcare.services.SetoresService;
import com.zieg.petcare.services.UserService;


@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {
	
	@Autowired ProdutosRepository produtos;
	@Autowired TiposProdutosRepository tipos;
	@Autowired MovimentacoesRepository movimentacoes;	
	@Autowired SetoresService SetorService;
	@Autowired MovimentacaoService MovimentacaoService;
	@Autowired FornecedorService FornecedorService;
	@Autowired UserService userService;
		
	@GetMapping("/novo")
	public ModelAndView novo(Item items){
		ModelAndView mv = new ModelAndView("movimentacao/cadastro-movimentacao");
		mv.addObject("items",items);
		mv.addObject("tipoMovimento", TipoMovimentacao.values());
		mv.addObject("setores", SetorService.findSetorActive());
		mv.addObject("medida", TipoMedida.values());
		mv.addObject("Fornecedores", FornecedorService.findAll() );
		mv.addObject("tipoProduto", tipos.findAll());
		return mv;
	}	
	
	
	@PreAuthorize("hasAnyRole('ADMIN','FUNC')")
	@PostMapping("/novo")
	public ModelAndView salvar(Item items, RedirectAttributes attr) throws JsonParseException, JsonMappingException, IOException{
		
		if(items.getValor().isEmpty()){
			attr.addFlashAttribute("error", "Por favor insira ao menos um item antes de finalizar as movimentações!");
			return new ModelAndView("redirect:/movimentacao/novo");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		List<ItemMovimentacao> listItens = mapper.readValue(items.getValor(), TypeFactory.defaultInstance()
					  														   .constructCollectionType(List.class, ItemMovimentacao.class));
		
		
		try {
			MovimentacaoService.salvar(listItens);
			
			int qtdRegistro = listItens.size();
			
			attr.addFlashAttribute("mensagem", qtdRegistro + " Movimentação(s) inserida(s) com sucesso!");
			return new ModelAndView("redirect:/movimentacao");
			
		} catch (Exception e) {
			attr.addFlashAttribute("error", e.toString());
			return new ModelAndView("redirect:/movimentacao/novo");
		}
		
		

		
	}

	@GetMapping
	public ModelAndView pesquisar(){
		ModelAndView mv = new ModelAndView("movimentacao/lista-movimentacao");
		mv.addObject("tipoMovimento", TipoMovimentacao.values());
		mv.addObject("setores", SetorService.findSetorActive());
		mv.addObject("produtos", produtos.findAll());
		mv.addObject("movimentacoes", movimentacoes.findAll());
		mv.addObject("usuario", userService.findAll());
		return mv;
	}	
	

	
	
}
