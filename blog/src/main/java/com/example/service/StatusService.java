package com.example.service;

import com.example.entity.Status;
import com.example.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusService {

    public Status getById(int id);

    public List<Status> getByStudent(Student student);

    public List<Status> getAll();

    public Status save(Status status);

    public Status update(int id,Status status);

    public boolean delete(int id);

}
