package com.arthur.webtech.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.webtech.quiz.model.Doctor;
import com.arthur.webtech.quiz.service.DoctorService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

  private final DoctorService doctorService;

  public DoctorController(DoctorService doctorService) {
    this.doctorService = doctorService;
  }
  @PostMapping
  public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
    Doctor saved = doctorService.createDoctor(doctor);
    return ResponseEntity.status(201).body(saved);
  }

  @GetMapping
  public ResponseEntity<List<Doctor>> getAllDoctors() {
    List<Doctor> doctors = doctorService.getAllDoctors();
    return ResponseEntity.ok(doctors);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getDoctorById(@PathVariable Long id) {
    try {
      Doctor doctor = doctorService.getDoctorById(id);
      return ResponseEntity.ok(doctor);
    } catch (RuntimeException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
    try {
      Doctor updated = doctorService.updateDoctor(id, doctor);
      return ResponseEntity.ok(updated);
    } catch (RuntimeException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    }
  }
}
