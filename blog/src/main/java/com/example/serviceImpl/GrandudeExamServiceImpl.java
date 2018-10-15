package com.example.serviceImpl;

import com.example.entity.GrandudeExam;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.GrandudeExamRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.service.GrandudeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrandudeExamServiceImpl implements GrandudeExamService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GrandudeExamRepository grandudeExamRepository;

    @Override
    public GrandudeExam getById(int id){
        return grandudeExamRepository.findById(id);
    }

    @Override
    public GrandudeExam getByStudentAndSubject(Student student, Subject subject) {
        return grandudeExamRepository.findByStudentAndSubject(student,subject);
    }

    @Override
    public List<GrandudeExam> getAll() {
        return grandudeExamRepository.findAll();
    }

    @Override
    public List<GrandudeExam> getByStudent(Student student){
        return grandudeExamRepository.findByStudent(student);
    }

    @Override
    public List<GrandudeExam> getBySubject(Subject subject){
        return grandudeExamRepository.findBySubject(subject);
    }

    private Student updateAvg(GrandudeExam grandudeExam){
        Student student = studentRepository.findById(grandudeExam.getStudent().getId());
        List<GrandudeExam> list = this.getByStudent(student);
        if (list.size() == 0){
            Student student1 = new Student(student.getId(),student.getName(),student.getBirth(),student.getAddress(),student.getImage(),student.getUsername(),student.getPassword());
            return studentRepository.save(student1);
        }
        float avg = 0;
        for (int i = 0;i < list.size();i++){
            avg += list.get(i).getAvg() / list.size();
        }
        student.setAvg(avg);
        return studentRepository.save(student);
    }

    @Override
    public boolean delete(int id){
        if(grandudeExamRepository.findById(id) != null){
            GrandudeExam grandudeExam = grandudeExamRepository.findById(id);
            grandudeExamRepository.deleteById(id);
            this.updateAvg(grandudeExam);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public GrandudeExam save(GrandudeExam grandudeExam){
        GrandudeExam exam = grandudeExamRepository.save(grandudeExam);
        this.updateAvg(exam);
        exam = grandudeExamRepository.findById(exam.getId());
        return exam;
    }

    @Override
    public GrandudeExam update(int id, GrandudeExam grandudeExam){
        if(grandudeExamRepository.findById(id) != null){
            grandudeExam.setId(id);
            GrandudeExam exam = grandudeExamRepository.save(grandudeExam);
            this.updateAvg(exam);
            exam = grandudeExamRepository.findById(exam.getId());
            return exam;
        }else {
            return null;
        }
    }

    @Override
    public boolean isExist(GrandudeExam grandudeExam) {
        if(grandudeExamRepository.findById(grandudeExam.getId()) != null || grandudeExamRepository.findByStudentAndSubject(grandudeExam.getStudent(),grandudeExam.getSubject()) != null){
            return true;
        }else {
            return false;
        }
    }
}
