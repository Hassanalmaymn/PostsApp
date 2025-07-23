package com.example.app.DTO;

import java.time.LocalDateTime;

public class PostDTO {
	private String title;
	private String content;
	private LocalDateTime created_at;

	public PostDTO() {
		super();
	}

	public PostDTO(String title, String content, LocalDateTime created_at) {
		super();
		this.title = title;
		this.content = content;
		this.created_at = created_at;
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

}
