package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@GetMapping
	public List<User> retrieveUsers() {
		return userRepository.findAll();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		System.out.println(id);
		userRepository.deleteById(id);
	}

	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable("id") long id) {
		System.out.println(id);
		return userRepository.findById(id);
	}
}
