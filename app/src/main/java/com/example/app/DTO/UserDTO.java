package com.example.app.DTO;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDTO {
	private Long id;

	@Autowired
	public UserDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
