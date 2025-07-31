package com.example.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.PostDTO;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.service.PostService;
import com.example.app.service.UserPrincipal;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    //	private final PostRepository postRepository;
    private final PostService postService;

    @Autowired
    public PostsController(PostService postservices) {
        super();
//		this.postRepository = postRepository;
        this.postService = postservices;
    }

    @GetMapping
    public Page<PostDTO> getAllPosts(@RequestParam(name = "pageNumber") int pageNumber,
                                     @RequestParam(name = "pageSize") int pageSize) {
        return postService.getAllPosts(pageNumber, pageSize);
    }

    @GetMapping(path = "/getUserPosts/{user_id}")
    public List<PostDTO> getPostsForSpecificUser(@PathVariable("user_id") long id) {
        return postService.findByUserId(id);
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('POST_CREATE')")
    public void saveNewPost(@RequestBody Post post) {
        postService.save(post);
    }

    @GetMapping(path = "/{post_id}")
    public PostDTO getSpecificPost(@PathVariable("post_id") long post_id) {
        return postService.findPostById(post_id)
                .orElseThrow(() -> new ResourceNotFoundException("no post with id:" + post_id));
    }

    @GetMapping(path = "/{post_id}/getCategories")
    public ResponseEntity<List<Category>> getPostCategories(@PathVariable("post_id") long post_id) {
        return getPostCategories(post_id);
    }

    @PostMapping(path = "/delete/{post_id}")
    @PreAuthorize("hasAuthority('POST_DELETE')")
    public ResponseEntity<String> deletePost(@PathVariable("post_id") long post_id, @AuthenticationPrincipal UserPrincipal principal) {
        try {

            postService.deletePostByIdWithAuthorization(post_id, principal.getUsername());
            return ResponseEntity.ok("Post deleted");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this post");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }

    @GetMapping("/search")
    public List<PostDTO> searchByKeyword(@RequestParam(name = "keyword", required = false) String keyword) {

        return postService.searchPostsByKeyword(keyword);
    }

}
