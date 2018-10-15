package com.example.service;

import com.example.entity.Comment;
import com.example.entity.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    public Comment getById(int id);

    public List<Comment> getByStatus(Status status);

    public List<Comment> getAll();

    public Comment save(Comment comment);

    public Comment update(int id,Comment comment);

    public boolean delete(int id);
}
