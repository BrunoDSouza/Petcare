package com.zieg.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zieg.petcare.model.authentication.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {

		public List<Role> findByDescricao(String role);

}

