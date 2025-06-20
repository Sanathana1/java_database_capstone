package com.smartclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import com.smartclinic.model.Prescription;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @PostMapping
    public ResponseEntity<String> savePrescription(@Valid @RequestBody Prescription prescription, @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return new ResponseEntity<>("Unauthorized - Missing token", HttpStatus.UNAUTHORIZED);
        }

        // Dummy validation logic
        if (prescription.getPatientId() == null || prescription.getDoctorId() == null) {
            return new ResponseEntity<>("Invalid prescription data", HttpStatus.BAD_REQUEST);
        }

        // Simulate saving
        return new ResponseEntity<>("Prescription saved successfully", HttpStatus.CREATED);
    }
}
