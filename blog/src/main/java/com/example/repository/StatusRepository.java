package com.example.repository;

import com.example.entity.Status;
import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findById(int id);
    List<Status> findByStudent(Student student);
}
