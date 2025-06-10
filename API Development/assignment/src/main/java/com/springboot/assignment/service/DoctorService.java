package com.springboot.assignment.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.assignment.model.Doctor;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.PatientDoctor;
import com.springboot.assignment.model.User;
import com.springboot.assignment.repository.DoctorRepository;
import com.springboot.assignment.repository.PatientDoctorRepository;
import com.springboot.assignment.repository.UserRepository;

@Service
public class DoctorService {
	 @Autowired
	    private DoctorRepository doctorRepository;

	    @Autowired
	    private UserService userService;
	    @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private PatientDoctorRepository patientDoctorRepository;

	    
	    //creating Doctor
	    public Doctor createDoctor(Doctor doctor) {
	        User user = doctor.getUser();
	        user.setRole("DOCTOR");
	        user = userService.signup(user);
	        doctor.setUser(user);
	        return doctorRepository.save(doctor);
	    }
	    
	    
	    //getting list of all patients by doctor id
	    public List<Patient> getPatientsByDoctor(int doctorId, String username) {
	   	   
	    	//checking authenticated user in user repository
	    	User user = userRepository.getByUsername(username);
	    	
	    	//if user is null it will return exception
	        if (user == null) {
	            throw new NoSuchElementException("User not found");
	        }
            
	        //check doctor by using their id 
	        Doctor doctor = doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new RuntimeException("Doctor not found"));
            
	        
	        //if doctor is not an authenticated user ,throw an exception
	        if (doctor.getUser().getId() != user.getId()) {
	            throw new RuntimeException("Access allowed only to the doctor");
	        }


	        List<PatientDoctor> appointments = patientDoctorRepository.findByDoctor(doctor);
	        
	        //list for store unique patients
	        List<Patient> patients = new ArrayList<>();
	       
	        //to avoid duplicates
	        Set<Integer> patientIds = new HashSet<>();

	        for (PatientDoctor appointment : appointments) {
	            Patient patient = appointment.getPatient();
	            
	          // check if patient is not null and not already added
	            if (patient != null && !patientIds.contains(patient.getId())) {
	                patients.add(patient);
	                patientIds.add(patient.getId());
	            }
	        }

	        return patients;

	    }
}
