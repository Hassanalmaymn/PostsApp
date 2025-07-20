package com.example.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Post;
import com.example.app.repository.PostRepository;

@RestController
public class PostsController {
	private final PostRepository postRepository;

	@Autowired
	public PostsController(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	@GetMapping(path = "/all_posts")
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@GetMapping(path = "/users/{id}/posts")
	public List<Post> getPostsForSpecificUser(@PathVariable("id") long id) {
		return postRepository.findByUserId(id);
	}

	@PostMapping(path = "/users/{id}/posts")
	public void saveNewPost(@RequestBody Post post) {
		postRepository.save(post);
	}

	@GetMapping(path = "/users/{id}/posts/{post_id}")
	public Optional<Post> getSpecificPostForSpecificUser(@PathVariable("post_id") long post_id) {
		return postRepository.findById(post_id);
	}

}
