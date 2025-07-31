package com.example.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private List<Privilege> privileges = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(long id, List<Privilege> privileges, String name) {
        this.id = id;
        this.privileges = privileges;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
