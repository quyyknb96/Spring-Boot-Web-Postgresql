package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "school")
public class Subject {
    private int id;
    private String name;
    private String decription;
    private List<GrandudeExam> grandudeExams;

    public Subject() {
    }

    public Subject(String name, String decription) {
        this.name = name;
        this.decription = decription;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "decription")
    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                Objects.equals(name, subject.name) &&
                Objects.equals(decription, subject.decription);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", decription='" + decription + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, decription);
    }

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    public List<GrandudeExam> getGrandudeExams() {
        return grandudeExams;
    }

    public void setGrandudeExams(List<GrandudeExam> grandudeExams) {
        this.grandudeExams = grandudeExams;
    }
}
