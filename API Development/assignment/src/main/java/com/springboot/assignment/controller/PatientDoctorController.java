package com.springboot.assignment.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.PatientDoctor;
import com.springboot.assignment.service.PatientDoctorService;

@RestController
public class PatientDoctorController {

	@Autowired
	private PatientDoctorService patientDoctorService;
	
	
	 //Post : Appointment
	 //Path : /api/book/patientId/doctorId
	 //Request : patientId,doctorId
	
	 @PostMapping("/api/book/{patientId}/{doctorId}")
	    public PatientDoctor bookAppointment(
	            @PathVariable int patientId,
	            @PathVariable int doctorId,
	            @RequestBody PatientDoctor patientDoctor) {
		        return patientDoctorService.bookAppointment(patientId, doctorId,patientDoctor);
	    }
	 
	 @GetMapping("/api/appointment/patient")
	 public List<Patient> getPatientsByDate(
			 @RequestParam LocalDate date){
		 return patientDoctorService.getPatientsByDate(date);
	 }
}
