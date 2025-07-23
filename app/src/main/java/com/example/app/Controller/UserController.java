package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.User;
import com.example.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping
	public List<User> retrieveUsers() {
		return userService.retrieveUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/{id}/deleteUser")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}

	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable("id") long id) {
		return userService.getUser(id);
	}
}
