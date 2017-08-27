package com.zieg.petcare.model.authentication;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsCustom extends Users implements UserDetails {

	private static final long serialVersionUID = 1L;

	public UserDetailsCustom(final Users users) {
		super(users);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return super.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getDescricao()))
					.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return super.getSenha();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}
	
	@Override
	public void setUsername(String username){
		super.setUsername(username);
	}

	@Override
	public boolean isAccountNonExpired() {
		return super.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return super.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return super.isEnabled();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}

}
