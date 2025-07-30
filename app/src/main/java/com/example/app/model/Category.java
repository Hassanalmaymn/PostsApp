package com.example.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category_name;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> posts = new ArrayList<>();

    public Category() {
        super();
    }

    public Category(long id, String category_name) {
        super();
        this.id = id;
        this.category_name = category_name;
    }

    public long getId() {
        return id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}
