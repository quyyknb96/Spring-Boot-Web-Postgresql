package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findById(int id);
    List<Student> findByName(String name);
    Student findByUsernameAndPassword(String username, String password);
    Student findByUsername(String username);
}
