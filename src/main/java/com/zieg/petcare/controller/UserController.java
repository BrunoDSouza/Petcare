package com.zieg.petcare.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zieg.petcare.model.authentication.Users;
import com.zieg.petcare.model.enums.TipoStatus;
import com.zieg.petcare.repository.RolesRepository;
import com.zieg.petcare.services.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired private UserService userService;
	@Autowired private RolesRepository roleRepository;
		
	
	@GetMapping("/settings/profile")
	public ModelAndView profile(Users user){
		
		if(user.isEmpty()){
			user = userService.getUserPrincipal();
		}
		
		ModelAndView mv = new ModelAndView("/users/edit-perfil");
		mv.addObject("usuario", user);
		return mv;
	}
	
	@PutMapping("/settings/update")
	public ModelAndView update(@ModelAttribute("usuario") Users user, BindingResult result,
						 	   RedirectAttributes attr){
		
		Users userLogin = userService.getUpdates(user);
		
		
		result = userService.isValidUser(userLogin, result);
		
		if(result.hasErrors())
			return profile(user);
		
		userService.save(userLogin);
		userService.UpdateContextSecurity(userLogin);
		
		attr.addFlashAttribute("mensagem", "Dados alterados com sucesso!");
		return new ModelAndView("redirect:/users/settings/profile");
		
	}

	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ModelAndView getListUsers(){
		
		ModelAndView mv = new ModelAndView("/users/lista-usuario");
		mv.addObject("users", userService.findUsers());
		
		return mv;
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/novo")
	public ModelAndView novo(Users usuario){
		
		ModelAndView mv = new ModelAndView("users/cadastro-usuario");
		mv.addObject("usuario",usuario);
		mv.addObject("roles", roleRepository.findAll());
		mv.addObject("Status", TipoStatus.values());
		
		return mv;
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/novo")
	public ModelAndView save(@Valid @ModelAttribute("usuario") Users user, BindingResult result,
							   RedirectAttributes attr){
		
		
		if(result.hasErrors())
			return novo(user);
		
		userService.save(user);

		attr.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/users");
		
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{codigo}")
	public ModelAndView edit(@PathVariable Long codigo){

		Users user = userService.findOne(codigo);
		return novo(user);
	} 
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{codigo}")
	public String delete(@PathVariable Long codigo, RedirectAttributes attr){
		
		Users user =  userService.findOne(codigo);
		userService.delete(user);
		
		attr.addFlashAttribute("mensagem", "Usuário removido com sucesso!");
		return "redirect:/users";
		
	}
	
	
	
	
}
