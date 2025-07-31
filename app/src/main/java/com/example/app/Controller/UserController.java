package com.example.app.Controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.Email;
import com.example.app.DTO.LoginRequest;
import com.example.app.DTO.UserDTO;
import com.example.app.model.User;
import com.example.app.service.ImplUserDetailsService;
import com.example.app.service.JWTService;
import com.example.app.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;
	private final ImplUserDetailsService implUserDetailsService;

	@Autowired
	public UserController(UserService userService, AuthenticationManager authenticationManager, JWTService jwtService,
			ImplUserDetailsService implUserDetailsService) {

		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.implUserDetailsService = implUserDetailsService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest,
			HttpServletResponse response) throws AuthenticationException, UsernameNotFoundException {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		UserDetails user = implUserDetailsService.loadUserByUsername(request.getEmail());
		String JWT = jwtService.generateToken(user);
//		Cookie cookie = new Cookie("jwt", JWT);
//		cookie.setHttpOnly(true);
//		cookie.setSecure(false);
//
//		cookie.setPath("/");
//		cookie.setMaxAge(24 * 60 * 60); // 1 day
//		response.addCookie(cookie);
		ResponseCookie cookie = ResponseCookie.from("jwt", JWT)
				.httpOnly(true)
				.secure(false) // true in prod
				.path("/")
				.maxAge(Duration.ofDays(1))
				.sameSite("lax")
				.build();

		response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

		Authentication auth = authenticationManager.authenticate(token);
		if (!auth.isAuthenticated()) {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}

		Optional<UserDTO> userDTO = userService.getByEmail(request.getEmail());
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("jwt", JWT);
		responseBody.put("user", userDTO);

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);

	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie cookie = new Cookie("access_token", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); // if using HTTPS
		cookie.setPath("/");
		cookie.setMaxAge(0); // delete it
		response.addCookie(cookie);
		return ResponseEntity.ok("Logged out");
	}
//
//	@PostMapping("/getUserByEmail")
//	public Optional<UserDTO> getUserByEmail(@RequestBody Email email) {
//		System.out.println(email);
//		return userService.getByEmail(email.getEmail());
//
//	}

}
