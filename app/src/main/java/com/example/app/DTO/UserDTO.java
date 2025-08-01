package com.example.app.DTO;

import com.example.app.model.Role;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private List<RoleDTO> roles;

    public UserDTO() {
    }


    public UserDTO(Long id, String name, String email, List<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
