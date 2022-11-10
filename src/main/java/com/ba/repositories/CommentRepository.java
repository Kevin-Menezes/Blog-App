package com.ba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ba.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
