package com.smartclinic.repository;

import com.smartclinic.model.Patient;
import java.util.List;

public interface PatientRepository {

    // Custom query method to retrieve by email
    Patient findByEmail(String email);

    // Method to retrieve by email or phone number
    List<Patient> findByEmailOrPhone(String email, String phone);
}
