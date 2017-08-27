package com.zieg.petcare.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zieg.petcare.model.Produto;
import com.zieg.petcare.model.enums.TipoMedida;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.services.FornecedorService;
import com.zieg.petcare.services.ProdutoService;
import com.zieg.petcare.services.TipoProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired ProdutoService produtoService;
	@Autowired FornecedorService fornecedorService;
	@Autowired TipoProdutoService tiposService;
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/novo")
	public ModelAndView novo(Produto produto){
		ModelAndView mv = new ModelAndView("produto/cadastro-produto");
		mv.addObject(produto);
		mv.addObject("medida", TipoMedida.values());
		mv.addObject("Fornecedores", fornecedorService.getFornecedores() );
		mv.addObject("tipoProduto", tiposService.findByStatus(TipoStatus.ATIVADO));
		mv.addObject("Status", TipoStatus.values());
		return mv;
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Produto produto, BindingResult result,
								RedirectAttributes attr){
		if(result.hasErrors())
			return novo(produto);
		
		produtoService.save(produto);
		attr.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
		return new ModelAndView("redirect:/produto");
	}
	
	@GetMapping
	public ModelAndView pesquisar(){
		
		ModelAndView mv = new ModelAndView("produto/lista-produto");
		mv.addObject("medida", TipoMedida.values());
		mv.addObject("Fornecedores", fornecedorService.findAll());
		mv.addObject("tipoProduto", tiposService.findAll());
		mv.addObject("produtos", produtoService.findAll());
		return mv;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
		
		Produto produto = produtoService.findOne(codigo);
		return novo(produto);

	}
	
}
