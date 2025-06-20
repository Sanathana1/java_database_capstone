package com.smartclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.smartclinic.model.Appointment;

public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    // Method to save appointment (3 points)
    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Method to get appointments for a doctor on a specific date (3 points)
    public List<Appointment> getAppointmentsForDoctor(String doctorId, LocalDate date) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appt : appointments) {
            if (appt.getDoctorId().equals(doctorId) && appt.getDate().equals(date)) {
                result.add(appt);
            }
        }
        return result;
    }
}
