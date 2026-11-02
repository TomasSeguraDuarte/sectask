package com.spring.sectask.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.sectask.model.Users;
import com.spring.sectask.repository.RolesRepository;
import com.spring.sectask.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private RolesRepository roleRepository;
	
	@GetMapping
	public String getAllUsers(Model model) {
		model.addAttribute("users",userService.getAllUsers());
		return "users";
	}
	
	@GetMapping("/new")
	public String mostrarFormNuevoUser(Model model) {
		model.addAttribute("users", new Users());
		return "new";
	}
	
	@PostMapping("/create")
	public String crearUsuario(@ModelAttribute Users user, @RequestParam List<String> rolesSeleccionados) {
		userService.createUser(user, rolesSeleccionados);
		return "redirect:/users";
	}
	
	@GetMapping("/edit/{id}")
	public String mostrarFormEditarUser(@PathVariable Long id, Model model) {
		Users user = userService.getUserById(id).orElseThrow();
		model.addAttribute("user", user);
		model.addAttribute("roles", roleRepository.findAll());
		return "edit";
	}
	
	@PostMapping("/update/{id}")
	public String actualizarUser(@PathVariable Long id, @ModelAttribute Users user, @RequestParam String rol) {
		userService.updateUsers(id, user, rol);
		return "redirect:/users";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUsers(id);
		return "redirect:/users";
	}
	
	@GetMapping("/403")
	public String accesoDenegado() {
		return "403";
	}
}
