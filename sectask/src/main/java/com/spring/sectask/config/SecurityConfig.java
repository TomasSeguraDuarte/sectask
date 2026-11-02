package com.spring.sectask.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean PasswordEncoder PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/403").permitAll()
				.requestMatchers("/users").permitAll()
				.requestMatchers("/users/delete/**").hasAnyAuthority("ADMIN")
				.requestMatchers("/users/edit/**").hasAnyAuthority("ADMIN","EDITOR")
				.requestMatchers("/users/new").hasAnyAuthority("ADMIN","CREATOR")
				.anyRequest().authenticated()
				)
				.formLogin(form -> form .defaultSuccessUrl("/users").permitAll())
				.logout(form -> form.permitAll())
				.exceptionHandling(e -> e.accessDeniedPage("/users/403"));
		return http.build();
	}
}
