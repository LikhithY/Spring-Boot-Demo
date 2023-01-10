package com.example.demoForJpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToMany
    @JoinTable(
            name="student_enrolled",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<>();

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    private String subjectName;

    public Long getId() {
        return id;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName){
        this.subjectName = subjectName;
    }


    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }
}