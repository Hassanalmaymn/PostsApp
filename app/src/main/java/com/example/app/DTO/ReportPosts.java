package com.example.app.DTO;

import java.time.LocalDateTime;

public class ReportPosts {
    private String title;
    private String content;
    private String author;
    private LocalDateTime created_at;

    public ReportPosts(String title, String content, String author, LocalDateTime created_at) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.created_at = created_at;
    }

    public ReportPosts() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
