package com.example.app.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.repository.CategoryRepository;

import java.util.List;

@RestController
public class CategoriesController {
	private final CategoryRepository categoryRepository;

	public CategoriesController(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/categories/{category_id}/getPosts")
	public ResponseEntity<List<Post>> getAllPostsForTheCategory(@PathVariable("category_id") long category_id) {
		var categoryOptional = categoryRepository.findById(category_id);
		if (categoryOptional.isPresent()) {
			List<Post> posts = categoryOptional.get().getPosts();
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@PostMapping("/categories")
	public void addNewCategory(@RequestBody Category category) {
		categoryRepository.save(category);
	}

	@DeleteMapping("/categories/{category_id}")
	public void deleteCategory(@PathVariable("category_id") long category_id) {
		categoryRepository.deleteById(category_id);
	}
}
