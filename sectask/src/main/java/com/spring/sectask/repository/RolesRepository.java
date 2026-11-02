package com.spring.sectask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.sectask.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

	Roles findByRole(String role);
}
