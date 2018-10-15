package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.Status;
import com.example.entity.Student;
import com.example.service.CommentService;
import com.example.service.StatusService;
import com.example.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class StatusController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/status/post", method = RequestMethod.POST)
    public String postStatus(Model model, HttpSession session, Status status){
        Date date = new Date();
        status.setDate(new Timestamp(date.getTime()));
        statusService.save(status);
        return "redirect:/loadSession";
    }

    @RequestMapping(value = "/status/delete", method = RequestMethod.POST)
    public String deleteStatus(Status status){
        statusService.delete(status.getId());
        return "redirect:/loadSession";
    }
    @RequestMapping(value = "/status/edit", method = RequestMethod.POST)
    public String editStatus(Status status){
        String c = status.getContent();
        Status s = statusService.getById(status.getId());
        s.setContent(c);
        statusService.update(status.getId(),s);
        return "redirect:/loadSession";
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public String getStatus(@PathVariable int id, Model model){
        Status status = statusService.getById(id);
        model.addAttribute("status",status);
        return "status";
    }

    @RequestMapping(value = "status/comment/post" , method = RequestMethod.GET)
    public ResponseEntity<?> postComment(Comment comment){
        Date date = new Date();
        comment.setDate(new Timestamp(date.getTime()));
        comment = commentService.save(comment);
        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }
    @RequestMapping(value = "status/comment/delete" , method = RequestMethod.GET)
    public ResponseEntity<?> deleteComment(Comment comment){
        commentService.delete(comment.getId());
        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }

    @RequestMapping(value = "status/comment/edit" , method = RequestMethod.GET)
    public ResponseEntity<?> editComment(Comment comment){
        Comment c = commentService.getById(comment.getId());
        c.setContent(comment.getContent());
        return new ResponseEntity<Comment>(commentService.update(c.getId(),c),HttpStatus.OK);
    }
}
