package com.example.serviceImpl;

import com.example.entity.Comment;
import com.example.entity.Status;
import com.example.entity.Student;
import com.example.repository.CommentRepository;
import com.example.repository.StatusRepository;
import com.example.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Status formatDate(Status status){
        Timestamp timestamp = status.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        status.setDatePrint(format.format(timestamp));
        for ( Comment c : status.getComments()
             ) {
            c.setDatePrint(format.format(c.getDate()));
        }
        return status;
    }

    private Status qsort(Status status){
        List<Comment> commentList = status.getComments();
        Collections.sort(commentList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                if(o1.getDate() == o2.getDate()){
                    return 0;
                }
                return o2.getDate().compareTo(o1
                        .getDate());
            }
        });
        status.setComments(commentList);
        return status;
    }

    @Override
    public Status getById(int id) {
        return this.formatDate(this.qsort(statusRepository.findById(id)));
    }

    @Override
    public List<Status> getByStudent(Student student) {
        return statusRepository.findByStudent(student);
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status update(int id, Status status) {
        if(statusRepository.findById(id) != null){
            status.setId(id);
            return statusRepository.save(status);
        }else {
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        Status status = statusRepository.findById(id);
        if( status != null){
            for (Comment comment : status.getComments()
                 ) {
                commentRepository.deleteById(comment.getId());
            }
            statusRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
