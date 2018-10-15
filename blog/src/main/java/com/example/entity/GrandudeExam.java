package com.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grandude_exam", schema = "school")
public class GrandudeExam {
    private int id;
    private Float avg;
    private Student student;
    private Subject subject;

    public GrandudeExam() {
    }

    public GrandudeExam(Float avg, Student student, Subject subject) {
        this.avg = avg;
        this.student = student;
        this.subject = subject;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "avg")
    public Float getAvg() {
        return avg;
    }

    public void setAvg(Float avg) {
        this.avg = avg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandudeExam that = (GrandudeExam) o;
        return id == that.id &&
                Objects.equals(avg, that.avg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, avg);
    }

    @Override
    public String toString() {
        return "GrandudeExam{" +
                "id=" + id +
                ", avg=" + avg +
                ", student=" + student +
                ", subject=" + subject +
                '}';
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
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
