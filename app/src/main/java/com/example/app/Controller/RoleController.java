package com.example.app.Controller;

import com.example.app.DTO.RoleDTO;
import com.example.app.model.Role;
import com.example.app.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")

public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public void addRole(@RequestBody Role role) {
        roleService.createNewRole(role);

    }
}
