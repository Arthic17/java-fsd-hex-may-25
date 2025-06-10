package com.springboot.assignment.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.assignment.model.Doctor;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.service.DoctorService;

@RestController
public class DoctorController {

	 @Autowired
	 private DoctorService doctorService;

	 
      //Post : Doctor
	  //Path : /api/doctor/add
	  //Request : doctor
	 
	    @PostMapping("/api/doctor/add")
	    public Doctor addDoctor(@RequestBody Doctor doctor) {
	        return doctorService.createDoctor(doctor);
	    }
	   
	    
	     //Get : Patient
	     //Path : /api/doctor/doctorid
		 //Request : doctorId
		 
	    
	    @GetMapping("/api/doctor/{doctorId}")
	    public List<Patient> getPatientsByDoctor(@PathVariable int doctorId, Principal principal) {
	  	        return doctorService.getPatientsByDoctor(doctorId, principal.getName());
	    }

}
