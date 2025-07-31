package com.example.app.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.app.DTO.RoleDTO;
import com.example.app.model.Role;
import com.example.app.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.DTO.UserDTO;
import com.example.app.model.User;
import com.example.app.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<UserDTO> retrieveUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<Role> roleOpt = roleRepository.findById(1l);
        if (roleOpt.isEmpty()) {
            throw new RuntimeException("role not found");
        }
        user.setRoles(List.of(roleOpt.get()));
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
        return userOptional.map(user -> {
            ModelMapper modelMapper = new ModelMapper();

            // Define how to map List<Role> → List<RoleDTO>
            TypeMap<User, UserDTO> typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
            typeMap.addMappings(mapper -> {
                mapper.map(
                        src -> Optional.ofNullable(src.getRoles())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(role -> new RoleDTO(role.getName()))
                                .collect(Collectors.toList()),
                        UserDTO::setRoles
                );
            });

            return modelMapper.map(user, UserDTO.class);
        });
    }
}
