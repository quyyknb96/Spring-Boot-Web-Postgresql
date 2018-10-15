package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/loadSession", method = RequestMethod.GET)
    public String load(HttpSession session){
        Student student = (Student) session.getAttribute("user");
        session.setAttribute("user",studentService.getById(student.getId()));
        return "redirect:/home";
    }

    @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    public String welcome(HttpSession session, Model model){
        if(session.getAttribute("user") == null){
            return "login";
        }else {
            model.addAttribute("currentUser",session.getAttribute("user"));
            return "index";
        }
    }
}