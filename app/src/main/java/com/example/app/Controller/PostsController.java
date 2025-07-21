package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.repository.PostRepository;
import com.example.app.service.PostServices;

@RestController
public class PostsController {
	private final PostRepository postRepository;
	private final PostServices postServices;

	@Autowired
	public PostsController(PostRepository postRepository, PostServices postservices) {
		super();
		this.postRepository = postRepository;
		this.postServices = postservices;
	}

	@GetMapping(path = "/all_posts")
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@GetMapping(path = "/users/{id}/posts")
	public List<Post> getPostsForSpecificUser(@PathVariable("id") long id) {
		return postRepository.findByUserId(id);
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/users/{id}/posts")
	public void saveNewPost(@RequestBody Post post) {
		postRepository.save(post);
	}

	@GetMapping(path = "/users/{id}/posts/{post_id}")
	public Optional<Post> getSpecificPostForSpecificUser(@PathVariable("post_id") long post_id) {
		return postRepository.findById(post_id);
	}

	@GetMapping(path = "/users/{id}/posts/{post_id}/getCategories")
	public ResponseEntity<List<Category>> getPostCategories(@PathVariable("post_id") long post_id) {
		Optional<Post> postOptional = postRepository.findById(post_id);

		if (postOptional.isPresent()) {
			List<Category> categories = postOptional.get().getCategories();
			return ResponseEntity.ok(categories);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(path = "/users/{id}/posts/{post_id}")
	public void deletePost(@PathVariable("post_id") long post_id) {
		postRepository.deleteById(post_id);
	}

	@GetMapping("/posts/search")
	public List<Post> search(@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "userId", required = false) Long userId,
			@RequestParam(name = "categoryId", required = false) Long categoryId) {
		return postServices.searchPosts(keyword, userId, categoryId);
	}

}
