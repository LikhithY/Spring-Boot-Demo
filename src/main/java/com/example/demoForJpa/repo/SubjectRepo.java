package com.example.demoForJpa.repo;

import com.example.demoForJpa.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
}
