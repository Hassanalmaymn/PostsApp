package com.example.app.DTO;

public class CategoryDTO {
	private String category_name;

	public CategoryDTO(String category_name) {
		super();
		this.category_name = category_name;
	}

	public CategoryDTO() {
		super();
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

}
