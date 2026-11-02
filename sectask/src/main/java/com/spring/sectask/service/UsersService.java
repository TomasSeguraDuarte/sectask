package com.spring.sectask.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.sectask.model.Roles;
import com.spring.sectask.model.Users;
import com.spring.sectask.repository.RolesRepository;
import com.spring.sectask.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RolesRepository roleRepository;
	
	public Users createUser(Users user, List<String> rolesSeleccionados) {
		if(usersRepository.existsByUsername(user.getUsername())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya existe");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (rolesSeleccionados != null) {
			for (String nombre : rolesSeleccionados) {
				Roles rol = roleRepository.findByRole(nombre);
				if (rol != null) {
					user.getRole().add(rol);
				}
			}
		}
		
		return usersRepository.save(user);
	}

	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}
	
	public Optional<Users> getUserById(Long id) {
		return usersRepository.findById(id);
	}
	
	public Users updateUsers(Long id, Users userDetails, String rol) {
		Users user = usersRepository.findById(id).orElseThrow();
		user.setUsername(userDetails.getUsername());
		user.setActive(userDetails.isActive());
		
		Roles role = roleRepository.findByRole(rol);
		if (role == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol inválido");
		}
		user.getRole().clear();
		user.getRole().add(role);
		
		return usersRepository.save(user);
	}
	
	public void deleteUsers(Long id) {
		Users user = usersRepository.findById(id).orElseThrow();
		usersRepository.delete(user);
	}
}
