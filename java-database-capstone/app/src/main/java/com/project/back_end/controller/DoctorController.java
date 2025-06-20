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
    @GetMapping("/doctors/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
    @PathVariable Long doctorId,
    @RequestParam String date,
    @RequestHeader("Authorization") String token,
    @RequestHeader("Role") String role) {

    // Step 1: Validate token
    boolean valid = authService.validateToken(token, role);
    if (!valid) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token or role");
    }

    // Step 2: Call service method to get availability
    List<String> availableSlots = doctorService.getAvailableSlots(doctorId, date);

    if (availableSlots == null || availableSlots.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No available slots found for given doctor and date.");
    }

    // Step 3: Return result
    Map<String, Object> response = new HashMap<>();
    response.put("doctorId", doctorId);
    response.put("date", date);
    response.put("availableSlots", availableSlots);

    return ResponseEntity.ok(response);
}

}
