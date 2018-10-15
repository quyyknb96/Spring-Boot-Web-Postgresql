package com.example.repository;

import com.example.entity.Comment;
import com.example.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(int id);
    List<Comment> findByStatus(Status status);
}
