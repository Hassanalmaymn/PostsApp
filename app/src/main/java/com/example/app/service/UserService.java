package com.example.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.DTO.UserDTO;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	public List<UserDTO> retrieveUsers() {
		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);

	}

	public Optional<UserDTO> getUser(long id) {
		return userRepository.findById(id).map(user -> modelMapper.map(user, UserDTO.class));
	}

}
