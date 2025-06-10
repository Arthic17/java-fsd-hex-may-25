package com.springboot.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.dto.PatientDto;
import com.springboot.assignment.service.PatientService;

@RestController
public class PatientController {
  
	@Autowired
	private PatientService patientService;
	
	
	
	 //Post : Patient
	 //Path : /api/patient/add
	 //Request : patientDto
	 
	@PostMapping("/api/patient/add")
	public PatientDto addPatient(@RequestBody PatientDto patientDto) {
		return patientService.addPatient(patientDto);
	}
	
	
	 //Get : Patient
	 //Path : /api/patient/patientId
	 //Request : patientId
	
	@GetMapping("/api/patient/{patientId}")
	public PatientDto getPatient(@PathVariable int patientId) {
	    return patientService.getPatient(patientId);
	}

}
