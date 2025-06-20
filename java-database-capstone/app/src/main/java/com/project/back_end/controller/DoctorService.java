package com.project.back_end.controller;

import com.project.back_end.model.Doctor;
import com.project.back_end.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable int id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "Doctor with ID " + id + " deleted successfully";
    }

    // Extra: validate login (you can call this from Question 10)
    @PostMapping("/login")
    public String validateDoctorLogin(@RequestParam String username, @RequestParam String password) {
        boolean valid = doctorService.validateLogin(username, password);
        return valid ? "Login successful" : "Invalid credentials";
    }

    // Extra: get available time slots by date
    @GetMapping("/{id}/available")
    public List<String> getAvailableTimeSlots(@PathVariable int id, @RequestParam String date) {
        return doctorService.getAvailableTimeSlots(id, date);
    }
}
