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

import com.zieg.petcare.model.Fornecedor;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.services.FornecedorService;
import com.zieg.petcare.services.UtilsService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	FornecedorService fornecedorService;	
	
	@Autowired
	UtilsService utilService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/novo")
	public ModelAndView novo(Fornecedor fornecedor){
		
		ModelAndView mv = new ModelAndView("fornecedor/cadastro-fornecedor");
		mv.addObject(fornecedor);
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
		
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Fornecedor fornecedor, BindingResult result,
								RedirectAttributes attr){
		if(result.hasErrors())
			return listar(fornecedor);
		
		fornecedorService.save(fornecedor);
		attr.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedor");
	}
	
	@GetMapping
	public ModelAndView listar(Fornecedor fornecedor){
		
		ModelAndView mv = new ModelAndView("fornecedor/lista-fornecedor");
		mv.addObject("fornecedores", utilService.maskCNPJ(fornecedorService.findAll()));
		mv.addObject(fornecedor);
		return mv;
	}
	
		
}
