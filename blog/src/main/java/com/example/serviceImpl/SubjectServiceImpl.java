package com.example.serviceImpl;

import com.example.entity.Subject;
import com.example.repository.SubjectRepository;
import com.example.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject getById(int id){
        return subjectRepository.findById(id);
    }

    @Override
    public List<Subject> getByName(String name){
        return subjectRepository.findByName(name);
    }

    @Override
    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    @Override
    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(int id,Subject subject){
        if(subjectRepository.findById(id) != null){
            subject.setId(id);
            return subjectRepository.save(subject);
        }else {
            return null;
        }
    }

    @Override
    public boolean delete(int id){
        if(subjectRepository.findById(id) != null){
            subjectRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isExist(Subject subject) {
        if(subjectRepository.findById(subject.getId()) != null){
            return true;
        }else {
            return false;
        }
    }
}

