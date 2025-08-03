package com.example.app.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.app.model.Category;

public class PostDTO {
	private long id;
	private long user_id;
	private String title;
	private String content;
	private String imageUrl;
	private LocalDateTime created_at;
	private List<Category> categories;

	public PostDTO() {
		super();
	}

	public PostDTO(long id, long user_id, String title, String content, String imageUrl, LocalDateTime created_at,
                   List<Category> categories) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
        this.imageUrl = imageUrl;
        this.created_at = created_at;
		this.categories = categories;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
