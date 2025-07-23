package com.example.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.app.DTO.CategoryDTO;
import com.example.app.model.Category;
import com.example.app.repository.CategoryRepository;
import com.example.app.specifications.categorySpecification;

@Service
public class CategoryService {
	CategoryRepository categoryRepository;
	ModelMapper modelMapper;

	public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<CategoryDTO> searchCategoriesByPostId(long postId) {
		Specification<Category> spec = categorySpecification.hasPostId(postId);
		List<Category> categories = categoryRepository.findAll(spec);
		return categories.stream().map((category) -> modelMapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());

//	    return categories.stream()
//	        .map(category -> modelMapper.map(category, CategoryDTO.class))
//	        .collect(Collectors.toList());
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
