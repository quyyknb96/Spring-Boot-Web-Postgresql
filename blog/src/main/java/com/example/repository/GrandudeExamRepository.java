package com.example.repository;

import com.example.entity.GrandudeExam;
import com.example.entity.Student;
import com.example.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrandudeExamRepository extends JpaRepository<GrandudeExam , Integer> {
    GrandudeExam findById(int id);
    List<GrandudeExam> findByStudent(Student student);
    List<GrandudeExam> findBySubject(Subject subject);
    GrandudeExam findByStudentAndSubject(Student student, Subject subject);
}
