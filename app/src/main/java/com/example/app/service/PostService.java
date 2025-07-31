package com.example.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.app.model.Role;
import com.example.app.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.app.DTO.PostDTO;
import com.example.app.DTO.UserDTO;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.model.Category;
import com.example.app.model.Post;
import com.example.app.repository.PostRepository;
import com.example.app.specifications.PostSpecifications;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public PostService(PostRepository postRepository, UserService userService, ModelMapper modelMapper, RoleRepository roleRepository) {
        super();
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.roleRepository = roleRepository;
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

    public Page<PostDTO> getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage.map((post) -> modelMapper.map(post, PostDTO.class));
    }

    public List<PostDTO> findByUserId(long id) {
        return postRepository.findByUserId(id).stream().map((post) -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    public void save(Post post) {
        postRepository.save(post);

    }

    public Optional<PostDTO> findPostById(long post_id) {
        Optional<Post> Opost = postRepository.findById(post_id);

        Optional<PostDTO> postOptional = Opost.map((post) -> modelMapper.map(post, PostDTO.class));
        postOptional.get().setUser_id(Opost.get().getUser().getId());
        if (postOptional.isEmpty()) {
            throw new ResourceNotFoundException("post not found");
        }

        return postOptional;
    }

    public ResponseEntity<List<Category>> getPostCategories(long post_id) {
        Optional<Post> postOptional = postRepository.findById(post_id);

        if (postOptional.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        List<Category> categories = postOptional.get().getCategories();
        return ResponseEntity.ok(categories);
    }

    public void deletePostById(long post_id, UserPrincipal principal) {

        postRepository.deleteById(post_id);
    }

    public void deletePostByIdWithAuthorization(long post_id, String username) {
        // Find user by username
        Optional<UserDTO> user = userService.getByEmail(username);
        if (user.isEmpty()) {
            throw new AccessDeniedException("User not found");
        }

        // Find post by ID
        Post post = postRepository.findById(post_id).orElseThrow(() -> new EntityNotFoundException("Post not found"));


        // Check authorization


        boolean isOwner = post.getUser().getId() == user.get().getId();
        boolean isAdmin = user.get().getRoles().stream()
                .anyMatch(role -> "ROLE_ADMIN".equals(role.getName()));
        if (isOwner || isAdmin) {
            postRepository.deleteById(post_id);
        } else {
            throw new AccessDeniedException("Not authorized");
        }

    }
}
