package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.repository.PostRepository;
import com.example.app.service.PostService;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
//	private final PostRepository postRepository;
	private final PostService postService;

	@Autowired
	public PostsController(PostRepository postRepository, PostService postservices) {
		super();
//		this.postRepository = postRepository;
		this.postService = postservices;
	}

	@GetMapping(path = "/all_posts")
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}

	@GetMapping(path = "/{user_id}")
	public List<Post> getPostsForSpecificUser(@PathVariable("user_id") long id) {
		return postService.findByUserId(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/")
	public void saveNewPost(@RequestBody Post post) {
		postService.save(post);
	}

	@GetMapping(path = "/{post_id}")
	public Optional<Post> getSpecificPostForSpecificUser(@PathVariable("post_id") long post_id) {
		return postService.findPostById(post_id);
	}

	@GetMapping(path = "/{post_id}/getCategories")
	public ResponseEntity<List<Category>> getPostCategories(@PathVariable("post_id") long post_id) {
		Optional<Post> postOptional = postService.findPostById(post_id);

		if (postOptional.isPresent()) {
			List<Category> categories = postOptional.get().getCategories();
			return ResponseEntity.ok(categories);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(path = "/delete/{post_id}")
	public void deletePost(@PathVariable("post_id") long post_id) {
		postService.deletePostById(post_id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/search")
	public List<Post> searchByKeyword(@RequestParam(name = "keyword", required = false) String keyword) {

		return postService.searchPostsByKeyword(keyword);
	}

}
