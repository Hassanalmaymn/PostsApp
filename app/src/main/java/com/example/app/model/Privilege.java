package com.example.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String privilege;

    @ManyToMany
    @JoinTable(name = "role_privilege", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "privilege_id"))
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    public Privilege() {
    }

    public Privilege(List<Role> roles, String privilege, Long id) {
        this.roles = roles;
        this.privilege = privilege;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
