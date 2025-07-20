package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.ToDo;

public interface TODORepository extends JpaRepository<ToDo, Long> {

	List<ToDo> findByUserId(Long userId);

}
