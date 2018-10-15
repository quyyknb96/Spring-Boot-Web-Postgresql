package com.example.service;

import com.example.entity.GrandudeExam;
import com.example.entity.Student;
import com.example.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrandudeExamService {

    public GrandudeExam getById(int id);

    public GrandudeExam getByStudentAndSubject(Student student, Subject subject);

    public List<GrandudeExam> getAll();

    public List<GrandudeExam> getByStudent(Student student);

    public List<GrandudeExam> getBySubject(Subject subject);

    public boolean delete(int id);

    public GrandudeExam save(GrandudeExam grandudeExam);

    public GrandudeExam update(int id, GrandudeExam grandudeExam);

    public boolean isExist(GrandudeExam grandudeExam);
}
