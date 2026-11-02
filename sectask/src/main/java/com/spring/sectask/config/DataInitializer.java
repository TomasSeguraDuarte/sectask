package com.spring.sectask.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.sectask.model.Roles;
import com.spring.sectask.model.Users;
import com.spring.sectask.repository.RolesRepository;
import com.spring.sectask.repository.UsersRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private RolesRepository roleRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		for (String nombre : List.of("ADMIN", "USER", "CREATOR", "EDITOR")) {
			if (roleRepository.findByRole(nombre) == null) {
				Roles r = new Roles();
				r.setRole(nombre);
				roleRepository.save(r);
			}
		}
		if (usersRepository.findByUsername("admin") == null) {
			Users admin = new Users();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("123"));
			admin.setActive(true);
			admin.getRole().add(roleRepository.findByRole("ADMIN"));
			usersRepository.save(admin);
		}
	}
}