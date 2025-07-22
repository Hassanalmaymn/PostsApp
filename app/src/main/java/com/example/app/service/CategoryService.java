package com.example.app.service;

import java.util.List;

import com.example.app.model.Category;
import com.example.app.repository.CategoryRepository;

public class CategoryService {
	CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public void addNewCategory(Category category) {
		categoryRepository.save(category);
	}

	public void deleteCategory(long category_id) {
		categoryRepository.deleteById(category_id);
	}
}
