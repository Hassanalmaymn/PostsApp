package com.example.app.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "role_privilege", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "privilege_id"))
    private List<Role> roles = new ArrayList<>();

    public Privilege() {
    }

    public Privilege(List<Role> roles, String name, Long id) {
        this.roles = roles;
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
