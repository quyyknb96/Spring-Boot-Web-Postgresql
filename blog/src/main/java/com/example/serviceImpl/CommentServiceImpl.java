package com.example.serviceImpl;

import com.example.entity.Comment;
import com.example.entity.Status;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private Comment formatDate(Comment comment){
        Timestamp timestamp = comment.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        comment.setDatePrint(format.format(timestamp));
        return comment;
    }

    @Override
    public Comment getById(int id) {
        return this.formatDate(commentRepository.findById(id));
    }

    @Override
    public List<Comment> getByStatus(Status status) {
        return commentRepository.findByStatus(status);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return this.formatDate(commentRepository.save(comment));
    }

    @Override
    public Comment update(int id, Comment comment) {
        if(commentRepository.findById(id) != null){
            comment.setId(id);
            return commentRepository.save(comment);
        }else {
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        if(commentRepository.findById(id) != null){
            commentRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
