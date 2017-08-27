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

import com.zieg.petcare.model.Setor;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.repository.SetoresRepository;

@Controller
@RequestMapping("/setor")
public class SetorController {

	@Autowired
	public SetoresRepository setores;
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Setor setor){
		ModelAndView mv = new ModelAndView("/setor/cadastro-setor");
		mv.addObject(setor);
		mv.addObject("Status", TipoStatus.values());
		return mv;
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Setor setor, BindingResult result,
							   RedirectAttributes attr){
		if(result.hasErrors())
		    return listar(setor);
		
		setores.save(setor);
		attr.addFlashAttribute("mensagem", "Setor salvo com sucesso!");
		return new ModelAndView("redirect:/setor");
		
	}
	
	@GetMapping
	public ModelAndView listar(Setor setor){
		
		ModelAndView mv = new ModelAndView("setor/lista-setor");
		mv.addObject("setores", setores.findAll());
		mv.addObject("setor", setor);
		mv.addObject("Status", TipoStatus.values());
		return mv;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo){
	    Setor setor = setores.findOne(codigo);
		return novo(setor);
	}
	
	
	
}
