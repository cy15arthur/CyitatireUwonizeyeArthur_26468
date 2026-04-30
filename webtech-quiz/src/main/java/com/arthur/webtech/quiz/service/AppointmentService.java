package com.arthur.webtech.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arthur.webtech.quiz.model.Appointment;
import com.arthur.webtech.quiz.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment bookAppointment(Appointment appointment) {
        List<Appointment> existing = appointmentRepository
                .findByDoctorIdAndAppointmentDate(appointment.getDoctorId(), appointment.getAppointmentDate());

        if (existing.size() > 0) {
            throw new RuntimeException("Doctor already has an appointment on this day.");
        }

        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id) {
        boolean exists = appointmentRepository.existsById(id);

        if (exists) {
            appointmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
    }
}
