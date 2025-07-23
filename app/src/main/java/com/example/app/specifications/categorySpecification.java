package com.example.app.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.example.app.model.Category;
import com.example.app.model.Post;

import jakarta.persistence.criteria.*;

public class categorySpecification {
	public static Specification<Category> hasPostId(Long postId) {
		return (root, query, cb) -> {
			Join<Category, Post> postJoin = root.join("posts");
			return cb.equal(postJoin.get("id"), postId);
		};
	}

}
