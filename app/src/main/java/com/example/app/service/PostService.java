package com.example.app.service;

import com.example.app.model.Post;
import com.example.app.repository.PostRepository;
import com.example.app.specifications.PostSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServices {

	@Autowired
	private PostRepository postRepository;

	public List<Post> searchPostsByKeywordTitle(String keyword) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (keyword != null && !keyword.isBlank()) {
			spec = spec.and(PostSpecifications.findByKeywordTitle(keyword));
		}

		return postRepository.findAll(spec);
	}

	public List<Post> searchPostsByKeywordContent(String keyword) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (keyword != null && !keyword.isBlank()) {
			spec = spec.and(PostSpecifications.findByKeywordContent(keyword));
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
}
