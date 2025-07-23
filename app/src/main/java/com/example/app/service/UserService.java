package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class UserService {
	UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> retrieveUsers() {

		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);

	}

	public Optional<User> getUser(long id) {
		return userRepository.findById(id);
	}

}
