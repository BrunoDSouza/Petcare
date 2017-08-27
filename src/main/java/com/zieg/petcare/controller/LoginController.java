package com.zieg.petcare.controller;



import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

	@GetMapping(value= {"/", "/login", "/logout"})
	public String login(Principal user){

		if(user != null)
			return "redirect:/home";
			
		
		return "login";
	}
	
}
