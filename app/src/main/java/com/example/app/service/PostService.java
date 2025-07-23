package com.example.app.service;

import com.example.app.DTO.PostDTO;
import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.repository.PostRepository;
import com.example.app.specifications.PostSpecifications;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	private final ModelMapper modelMapper;

	public PostService(PostRepository postRepository, ModelMapper modelMapper) {
		super();
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	public List<PostDTO> searchPostsByKeyword(String keyword) {
		Specification<Post> spec = (root, query, cb) -> cb.conjunction();

		if (keyword != null && !keyword.isBlank()) {
			spec = spec.and(PostSpecifications.findByKeyword(keyword));
		}

		return postRepository.findAll(spec).stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
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

	public List<PostDTO> getAllPosts() {
		return postRepository.findAll().stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
	}

	public List<PostDTO> findByUserId(long id) {
		return postRepository.findByUserId(id).stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
	}

	public void save(Post post) {
		postRepository.save(post);

	}

	public Optional<PostDTO> findPostById(long post_id) {

		return postRepository.findById(post_id).map((post) -> modelMapper.map(post, PostDTO.class));
	}

	public ResponseEntity<List<Category>> getPostCategories(long post_id) {
		Optional<Post> postOptional = postRepository.findById(post_id);

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
