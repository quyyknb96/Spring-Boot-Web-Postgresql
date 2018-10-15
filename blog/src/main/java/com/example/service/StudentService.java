package com.example.service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {


    public Student getById(int id);

    public List<Student> getByName(String name);

    public Student getByUsername(String username);

    public List<Student> getAll();

    public Student save(Student student);

    public Student update(int id, Student student);

    public boolean delete(int id);

    public boolean isExist(Student student);

    public boolean isExitsUsername(String username);

    public Student checkLogin(String username, String password);
}
