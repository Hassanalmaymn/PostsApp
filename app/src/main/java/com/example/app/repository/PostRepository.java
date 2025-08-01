package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.app.model.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> ,JpaSpecificationExecutor<Post>{

	List<Post> findByUserId(Long userId);

}
