package com.project.back_end.service;

import com.project.back_end.model.Doctor;
import com.project.back_end.model.Appointment;
import com.project.back_end.repository.DoctorRepository;
import com.project.back_end.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.*;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Get available time slots for a doctor on a given date.
     */
    public List<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);
        Set<LocalTime> bookedSlots = new HashSet<>();
        for (Appointment appt : appointments) {
            bookedSlots.add(appt.getTime());
        }

        // Example: Clinic working hours 9AM to 5PM, 30-min slots
        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        while (!start.isAfter(end)) {
            if (!bookedSlots.contains(start)) {
                allSlots.add(start);
            }
            start = start.plusMinutes(30);
        }

        return allSlots;
    }

    /**
     * Validate doctor login credentials and return response.
     */
    public Map<String, Object> validateDoctorLogin(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<Doctor> doctorOpt = doctorRepository.findByEmailAndPassword(email, password);
        if (doctorOpt.isPresent()) {
            response.put("status", "success");
            response.put("doctor", doctorOpt.get());
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid credentials");
        }
        return response;
    }
    public List<String> getAvailableSlots(Long doctorId, String date) {
    // Simulated logic — in real code, query DB
    List<String> slots = Arrays.asList("10:00", "11:00", "15:00");
    return slots; // replace with actual repository logic
}

}
