package com.springboot.assignment.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.assignment.model.Doctor;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.PatientDoctor;
import com.springboot.assignment.repository.DoctorRepository;
import com.springboot.assignment.repository.PatientDoctorRepository;
import com.springboot.assignment.repository.PatientRepository;

@Service
public class PatientDoctorService {
   
	private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
	private PatientDoctorRepository patientDoctorRepository; 
	public PatientDoctorService(PatientRepository patientRepository, DoctorRepository doctorRepository,
			PatientDoctorRepository patientDoctorRepository) {
	
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.patientDoctorRepository = patientDoctorRepository;
	}
	    
	    
	    public PatientDoctor bookAppointment(int patientId, int doctorId, PatientDoctor patientDoctor) {
	        
	    	//get patient id or throw exception
	    	Patient patient = patientRepository.findById(patientId)
	                .orElseThrow(() -> new RuntimeException("Patient not found"));
	    	
	    	//get doctor id or throw exception
	        Doctor doctor = doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new RuntimeException("Doctor not found"));

	       // Set the patient and doctor 
	        patientDoctor.setPatient(patient);
	        patientDoctor.setDoctor(doctor);
	        patientDoctor.setAppointmentTime(LocalDateTime.now());
	        

	        return patientDoctorRepository.save(patientDoctor);
	    }
	    
	    public List<Patient> getPatientsByDate(LocalDate date){
	  	return patientDoctorRepository.getPatientsByDate(date);
	    }
	    
	    
	
	
}
