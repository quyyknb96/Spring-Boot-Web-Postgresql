package com.example.service;

import com.example.entity.Subject;
import com.example.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    public Subject getById(int id);

    public List<Subject> getByName(String name);

    public List<Subject> getAll();

    public Subject save(Subject subject);

    public Subject update(int id,Subject subject);

    public boolean delete(int id);

    public boolean isExist(Subject subject);
}

