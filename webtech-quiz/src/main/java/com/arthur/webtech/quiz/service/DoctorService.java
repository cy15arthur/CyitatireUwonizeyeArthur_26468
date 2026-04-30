package com.arthur.webtech.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.webtech.quiz.model.Doctor;
import com.arthur.webtech.quiz.repository.DoctorRepository;

@Service
public class DoctorService {

  private final DoctorRepository doctorRepository;

  public DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }
  public List<Doctor> getAllDoctors() {
    return doctorRepository.findAll();
  }

  public Doctor getDoctorById(Long id) {
    return doctorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
  }

  public Doctor createDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
  }
  public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
    Doctor existing = getDoctorById(id);
    existing.setFullName(updatedDoctor.getFullName());
    existing.setSpecialization(updatedDoctor.getSpecialization());
    existing.setDepartment(updatedDoctor.getDepartment());
    existing.setAvailable(updatedDoctor.isAvailable());
    return doctorRepository.save(existing);
  }
}
