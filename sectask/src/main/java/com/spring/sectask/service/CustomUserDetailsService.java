package com.spring.sectask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sectask.model.Roles;
import com.spring.sectask.model.Users;
import com.spring.sectask.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users u = usersRepository.findByUsername(username);
		if (u == null) throw new UsernameNotFoundException("No existe");

		return User.withUsername(u.getUsername())
				.password(u.getPassword())      // el hash
				.disabled(!u.isActive())        // active=false → no puede entrar
				.authorities(u.getRole().stream()
						.map(Roles::getRole)    // el campo con el nombre del rol
						.toArray(String[]::new))
				.build();
	}

}
