package com.example.app.service;

import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.repository.PostRepository;
import com.example.app.specifications.PostSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> searchPostsByKeyword(String keyword) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (keyword != null && !keyword.isBlank()) {
			spec = spec.and(PostSpecifications.findByKeyword(keyword));
		}

		return postRepository.findAll(spec);
	}

	public List<Post> searchPostsUserId(Long userId) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (userId != null) {
			spec = spec.and(PostSpecifications.hasUserId(userId));
		}

		return postRepository.findAll(spec);
	}

	public List<Post> searchPostsByCategory(Long categoryId) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (categoryId != null) {
			spec = spec.and(PostSpecifications.hasCategoryId(categoryId));
		}

		return postRepository.findAll(spec);
	}

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	public List<Post> findByUserId(long id) {
		return postRepository.findByUserId(id);
	}

	public void save(Post post) {
		postRepository.save(post);

	}

	public Optional<Post> findPostById(long post_id) {

		return postRepository.findById(post_id);
	}

	public ResponseEntity<List<Category>> getPostCategories(long post_id) {
		Optional<Post> postOptional = findPostById(post_id);

		if (postOptional.isPresent()) {
			List<Category> categories = postOptional.get().getCategories();
			return ResponseEntity.ok(categories);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public void deletePostById(long post_id) {

		postRepository.deleteById(post_id);
	}
}
