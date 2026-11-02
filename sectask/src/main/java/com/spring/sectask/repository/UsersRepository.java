package com.spring.sectask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.spring.sectask.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	boolean existsByUsername(String username);
	Users findByUsername(String username);
}