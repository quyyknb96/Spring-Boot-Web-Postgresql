package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "student", schema = "school")
public class Student {
    private int id;
    private String name;
    private Date birth;
    private String address;
    private String image;
    private Float avg;
    private String username;
    private String password;
    private List<Comment> comments;
    private List<GrandudeExam> grandudeExams;
    private List<Status> statuses;

    public Student() {
    }


    public Student(String name, Date birth, String address, String image,String username, String password) {
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.image = image;
        this.username = username;
        this.password = password;
    }

    public Student(int id, String name, Date birth, String address, Float avg, String image,String username, String password) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.image  = image;
        this.avg = avg;
        this.username = username;
        this.password = password;
    }

    public Student(String name, Date birth, String address, Float avg,String image, String username, String password) {
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.image = image;
        this.avg = avg;
        this.username = username;
        this.password = password;
    }

    public Student(int id, String name, Date birth, String address,String image, String username, String password) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.image = image;
        this.username = username;
        this.password = password;
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
    @Column(name = "birth")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "avg")
    public Float getAvg() {
        return avg;
    }

    public void setAvg(Float avg) {
        this.avg = avg;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public List<GrandudeExam> getGrandudeExams() {
        return grandudeExams;
    }

    public void setGrandudeExams(List<GrandudeExam> grandudeExams) {
        this.grandudeExams = grandudeExams;
    }

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnore
    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
