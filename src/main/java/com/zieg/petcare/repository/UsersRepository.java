package com.zieg.petcare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zieg.petcare.model.authentication.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	public Optional<Users> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);	
	
	public Users findByUsernameIgnoreCaseAndCodigoNot(String username, Long codigo);
	public Users findByUsernameIgnoreCase(String username);
	
	public Users findByEmailIgnoreCaseAndCodigoNot(String email, Long codigo);
	public Users findByEmailIgnoreCase(String email);
	

	
}
