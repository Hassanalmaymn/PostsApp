package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.UserDTO;
import com.example.app.service.UserService;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	private final UserService userService;

	public AdminController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserDTO> retrieveUsers() {
		return userService.retrieveUsers();
	}

	@GetMapping("users/{id}")
	public Optional<UserDTO> getUser(@PathVariable("id") long id) {
		return userService.getUser(id);
	}

	@PostMapping("/users/{id}/deleteUser")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}

}
