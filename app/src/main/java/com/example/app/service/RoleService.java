package com.example.app.service;

import com.example.app.DTO.RoleDTO;
import com.example.app.model.Role;
import com.example.app.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createNewRole(Role role) {
        roleRepository.save(role);
    }
}
