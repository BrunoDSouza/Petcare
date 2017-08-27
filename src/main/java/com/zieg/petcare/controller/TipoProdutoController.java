package com.zieg.petcare.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zieg.petcare.model.TipoProduto;
import com.zieg.petcare.services.TipoProdutoService;
import com.zieg.petcare.model.enums.TipoStatus;

@Controller
@RequestMapping("/tipos")
public class TipoProdutoController {

	
	@Autowired
	TipoProdutoService TiposService;
		
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TipoProduto tipo, BindingResult result,
								RedirectAttributes attr){
		if(result.hasErrors())
			return listar(tipo);
		
		TiposService.save(tipo);
		attr.addFlashAttribute("mensagem", "Tipo de produto salvo com sucesso!");
		return new ModelAndView("redirect:/tipos");
	}
	
	
	@GetMapping
	public ModelAndView listar(TipoProduto tipo){
		
		ModelAndView mv = new ModelAndView("tipoproduto/lista-tipos");
		mv.addObject("tipoProdutos", TiposService.findAll());
		mv.addObject("tipo",tipo);
		mv.addObject("Status", TipoStatus.values());
		return mv;
	}
	

}
