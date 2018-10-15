package com.example.component;


import com.example.entity.GrandudeExam;
import com.example.service.GrandudeExamService;
import com.example.service.StudentService;
import com.example.entity.Student;
import com.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;

public class ComponentRunner{

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GrandudeExamService grandudeExamService;

    public void run(String... args) throws Exception {
        //GrandudeExam grandudeExam = new GrandudeExam(Float.parseFloat("9.5"),studentService.getById(1),subjectService.getById(1));
        //grandudeExamService.update(1,grandudeExam);
        //Student student = new Student("Tạ Ngọc Quý",Date.valueOf("1996-11-13"),"Ninh Bình");
        //studentService.update(1,student);
        //System.out.println(grandudeExamService.update(1,grandudeExam).toString());
        //System.out.println(student.getId());
    }
}
