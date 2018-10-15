package com.example.controller;

import com.example.common.GooglePojo;
import com.example.common.GoogleUtils;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session){
        if(session.getAttribute("user") != null){
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Student student, HttpSession session, Model result){
        student = studentService.checkLogin(student.getUsername(),student.getPassword());
        if(student != null){
            session.setAttribute("user",student);
            return "redirect:/";
        }else {
            result.addAttribute("error","Dang nhap khong thanh cong");
            return "login";
        }
    }

    @RequestMapping(value = "/login-google", method = RequestMethod.GET)
    public String loginGoogle(String code, HttpSession session, Model result) throws IOException {
        if( code != null || !code.isEmpty() ){
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            if(studentService.isExitsUsername(googlePojo.getId())){
                session.setAttribute("user",studentService.getByUsername(googlePojo.getId()));
            }else {
                Student student = new Student();
                student.setName(googlePojo.getName());
                student.setImage(googlePojo.getPicture());
                student.setUsername(googlePojo.getId());
                studentService.save(student);
                session.setAttribute("user",student);
            }
            return "redirect:/home";
        }else {
            result.addAttribute("error","Dang nhap khong thanh cong");
            return "login";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Student student, Model result){
        if(! studentService.isExitsUsername(student.getUsername())){
            studentService.save(student);
            return "redirect:/login";
        }else {
            result.addAttribute("error","Dang ky khong thanh cong");
            return "login";
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model){
        List<Student> studentList = studentService.getAll();
        model.addAttribute("userAll",studentList);
        return "user";
    }

    @RequestMapping(value = {"/u/{id}","/u/{id}/home"}, method = RequestMethod.GET)
    public String getStatusUser(@PathVariable int id, Model model){
        Student student = studentService.getById(id);
        model.addAttribute("currentUser",student);
        return "index";
    }

    @RequestMapping(value = "/u/{id}/profile", method = RequestMethod.GET)
    public String getProfileUser(@PathVariable int id ,Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("currentUser",student);
        return "profile";
    }

    @RequestMapping(value = "/profile/edit" , method = RequestMethod.POST)
    public String editProfile(HttpSession session, Student student, Model model){
        Student s = (Student) session.getAttribute("user");
        s.setName(student.getName());
        s.setAddress(student.getAddress());
        s.setBirth(student.getBirth());
        session.setAttribute("user",studentService.update(s.getId(),s));
        model.addAttribute("currentUser",session.getAttribute("user"));
        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("user");
        model.addAttribute("currentUser",student);
        return "profile";
    }
}
