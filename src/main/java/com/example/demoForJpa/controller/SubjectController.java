package com.example.demoForJpa.controller;

import com.example.demoForJpa.entity.Student;
import com.example.demoForJpa.entity.Subject;
import com.example.demoForJpa.repo.StudentRepo;
import com.example.demoForJpa.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class SubjectController {

    @Autowired
    SubjectRepo subjectRepo;

    @Autowired
    StudentRepo studentRepo;



    //The both GetMapping request below are used to retrieve the selected data related to subjects from the table.
    @GetMapping("/api/subjects")
    public ResponseEntity<List<Subject>> getSubjects(){
        return new ResponseEntity<>(subjectRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/subjects/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable long id){
        Optional<Subject> subject = subjectRepo.findById(id);
        if(subject.isPresent()){
            return new ResponseEntity<>(subject.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  The below PostMapping is used for updating the data to the table.
    @PostMapping("/api/subjects")
    public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject){
        return new ResponseEntity<>(subjectRepo.save(subject), HttpStatus.CREATED);
    }

    //  The below PutMapping is used for updating the specific data from the table.
    @PutMapping("/api/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable long id, @RequestBody Subject sub){
        Optional<Subject> subject = subjectRepo.findById(id);
        if(subject.isPresent()){
            subject.get().setSubjectName(sub.getSubjectName());
            return new ResponseEntity<>(subjectRepo.save(subject.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  The below code is used for Deleting the specific data from the table.
    @DeleteMapping("/api/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable long id){
        Optional<Subject> subject = subjectRepo.findById(id);
        if(subject.isPresent()){
            subjectRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // The below code is used to Join two tables in according to their unique_id.
    @PutMapping("/api/subjects/{subjectId}/students/{studentId}")
    public Subject enrollStudentToSubject(@PathVariable long subjectId , @PathVariable long studentId){
           Subject subject = subjectRepo.findById(subjectId).get();
           Student student = studentRepo.findById(studentId).get();
           subject.enrollStudent(student);
           return subjectRepo.save(subject);
    }

}
