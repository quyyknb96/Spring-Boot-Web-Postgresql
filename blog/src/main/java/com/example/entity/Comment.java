package com.example.entity;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "school")
public class Comment {
    private int id;
    private String content;
    private Timestamp date;
    private Student student;
    private Status status;
    private String datePrint;

    public Comment() {
    }

    public Comment(String content, Student student, Status status) {
        this.content = content;
        this.student = student;
        this.status = status;
    }

    public Comment(String content, Timestamp date, Student student, Status status) {
        this.content = content;
        this.date = date;
        this.student = student;
        this.status = status;
    }

    public Comment(int id, String content, Timestamp date, Student student, Status status) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.student = student;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDatePrint() {
        return datePrint;
    }

    public void setDatePrint(String datePrint) {
        this.datePrint = datePrint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(content, comment.content) &&
                Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, date);
    }


    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
