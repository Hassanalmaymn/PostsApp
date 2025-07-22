package com.example.app.specifications;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import com.example.app.model.Post;

public class PostSpecifications {

	public static Specification<Post> findByKeywordTitle(String title) {
		return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
	}

	public static Specification<Post> findByKeywordContent(String content) {
		return (root, query, cb) -> cb.like(cb.lower(root.get("content")), "%" + content.toLowerCase() + "%");
	}

	public static Specification<Post> hasUserId(Long userId) {
		return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
	}

	public static Specification<Post> hasCategoryId(Long categoryId) {
		return (root, query, cb) -> {
			Join<Object, Object> categories = root.join("categories");
			return cb.equal(categories.get("id"), categoryId);
		};
	}

}
