package com.arthur.webtech.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthur.webtech.quiz.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  
  
}
