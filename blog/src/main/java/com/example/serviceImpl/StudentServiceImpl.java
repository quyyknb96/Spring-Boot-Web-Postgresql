package com.example.serviceImpl;

import com.example.entity.Status;
import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private Student qsort(Student student){
        List<Status> statusList = student.getStatuses();
        Collections.sort(statusList, new Comparator<Status>() {
            @Override
            public int compare(Status o1, Status o2) {
                if(o1.getDate() == o2.getDate()){
                    return 0;
                }
                return o2.getDate().compareTo(o1
                        .getDate());
            }
        });
        student.setStatuses(statusList);
        return student;
    }

    private Student formatDate(Student student){
        List<Status> statusList = student.getStatuses();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for ( Status s : statusList
             ) {
            s.setDatePrint(format.format(s.getDate()));
        }
        student.setStatuses(statusList);
        return student;
    }

    @Override
    public Student getById(int id){
        Student student = studentRepository.findById(id);
        return this.formatDate(this.qsort(student));
    }

    @Override
    public List<Student> getByName(String name){
        return studentRepository.findByName(name);
    }

    @Override
    public Student getByUsername(String username) {
        return this.qsort(this.formatDate(studentRepository.findByUsername(username)));
    }

    @Override
    public List<Student> getAll(){ return studentRepository.findAll(); }

    @Override
    public Student save(Student student){
        return studentRepository.save(student);
    }

    @Override
    public Student update(int id, Student student){
        if(studentRepository.findById(id) != null){
            student.setId(id);
            return studentRepository.save(student);
        }else {
            return null;
        }
    }

    @Override
    public boolean delete(int id){
        Student student = studentRepository.findById(id);
        if(student != null){
            studentRepository.delete(student);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isExist(Student student) {
        if(studentRepository.findById(student.getId()) != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isExitsUsername(String username) {
        if(studentRepository.findByUsername(username) != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Student checkLogin(String username, String password) {
        Student student = studentRepository.findByUsernameAndPassword(username,password);
        if(student != null){
            return this.formatDate(this.qsort(student));
        }else {
            return null;
        }
    }
}
