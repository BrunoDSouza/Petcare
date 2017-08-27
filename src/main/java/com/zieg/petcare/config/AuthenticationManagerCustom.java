package com.zieg.petcare.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.zieg.petcare.services.UserService;

public class AuthenticationManagerCustom implements AuthenticationManager{
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
        String password = auth.getCredentials().toString();
        
        Authentication authorities = userService.getUserDetailsAuthentication();
        Collection<? extends GrantedAuthority> grantedAuths = authorities.getAuthorities();
        
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	}

	
}
