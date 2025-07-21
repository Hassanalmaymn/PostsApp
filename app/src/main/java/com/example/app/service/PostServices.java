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

	public List<Post> searchPosts(String keyword, Long userId, Long categoryId) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (keyword != null && !keyword.isBlank()) {
			spec = spec.and(PostSpecifications.findByKeyword(keyword));
		}

		if (userId != null) {
			spec = spec.and(PostSpecifications.hasUserId(userId));
		}

		if (categoryId != null) {
			spec = spec.and(PostSpecifications.hasCategoryId(categoryId));
		}

		return postRepository.findAll(spec);
	}
}
