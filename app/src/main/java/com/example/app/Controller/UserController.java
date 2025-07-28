package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.Email;
import com.example.app.DTO.LoginRequest;
import com.example.app.DTO.UserDTO;
import com.example.app.model.User;
import com.example.app.service.JWTService;
import com.example.app.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authenticationManager, JWTService jwtService) {

		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());

		Authentication auth = authenticationManager.authenticate(token);
		if (!auth.isAuthenticated()) {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}

		return ResponseEntity.ok(jwtService.generateToken(request.getEmail()));
	}

	@GetMapping("/users")
	public List<UserDTO> retrieveUsers() {
		return userService.retrieveUsers();
	}

	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return ResponseEntity.ok("Logged out");
	}

	@PostMapping("/users/{id}/deleteUser")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/getUserByEmail")
	public Optional<UserDTO> getUserByEmail(@RequestBody Email email) {
		System.out.println(email);
		return userService.getByEmail(email.getEmail());

	}

	@GetMapping("users/{id}")
	public Optional<UserDTO> getUser(@PathVariable("id") long id) {
		return userService.getUser(id);
	}
}
