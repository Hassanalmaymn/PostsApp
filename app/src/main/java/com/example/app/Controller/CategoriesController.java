package com.example.app.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.CategoryDTO;
import com.example.app.model.Category;
import com.example.app.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

//	@GetMapping("/categories/{category_id}/getPosts")
//	public ResponseEntity<List<Post>> getAllPostsForTheCategory(@PathVariable("category_id") long category_id) {
//		var categoryOptional = categoryRepository.findById(category_id);
//		if (categoryOptional.isPresent()) {
//			List<Post> posts = categoryOptional.get().getPosts();
//			return ResponseEntity.ok(posts);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CATEGORY_CREATE')")
    public void addNewCategory(@RequestBody Category category) {
        categoryService.addNewCategory(category);
    }

    @GetMapping("/{post_id}")
    public List<CategoryDTO> getAllCategories(@PathVariable(name = "post_id") long postId) {
        return categoryService.searchCategoriesByPostId(postId);
    }

    @PostMapping("/{category_id}/delete")
    @PreAuthorize("hasAuthority('CATEGORY_DELETE')")
    public void deleteCategory(@PathVariable("category_id") long category_id) {
        categoryService.deleteCategory(category_id);
    }
}
