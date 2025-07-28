package com.example.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.DTO.PostDTO;
import com.example.app.DTO.UserDTO;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public List<UserDTO> retrieveUsers() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	public Optional<UserDTO> getUser(long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return userOptional.map(user -> modelMapper.map(user, UserDTO.class));
	}

	public Optional<UserDTO> getByEmail(String email) {
		Optional<User> userOptional = userRepository.findUserByEmail(email);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return userOptional.map(user -> modelMapper.map(user, UserDTO.class));
	}
}
