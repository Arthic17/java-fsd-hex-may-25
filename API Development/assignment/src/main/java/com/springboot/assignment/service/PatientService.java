package com.springboot.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.assignment.dto.MedicalHistoryDto;
import com.springboot.assignment.dto.PatientDto;
import com.springboot.assignment.model.MedicalHistory;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.User;
import com.springboot.assignment.repository.MedicalHistoryRepository;
import com.springboot.assignment.repository.PatientRepository;

@Service
public class PatientService {

	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
   private UserService userService;
	public PatientService(PatientRepository patientRepository, MedicalHistoryRepository medicalHistoryRepository,
		UserService userService) {
		
		this.patientRepository = patientRepository;
		this.medicalHistoryRepository = medicalHistoryRepository;
		this.userService=userService;
	}
	
	public PatientDto addPatient(PatientDto dto) {

	    //extract user from dto
	    User user = dto.getUser();
	    if (user == null || user.getUsername() == null || user.getPassword() == null) {
	        throw new RuntimeException("Invalid user details");
	    }

	   //set role
	    user.setRole("PATIENT");
	    user = userService.signup(user); 

	    Patient patient = new Patient();
	    patient.setUser(user);
	    patient.setName(dto.getName());
	    patient.setAge(dto.getAge());

	    patient = patientRepository.save(patient);

	    // for saving medical histories
	    List<MedicalHistory> savedHistories = new ArrayList<>();
	    if (dto.getMedicalHistories() != null) {
	        for (MedicalHistoryDto mhDto : dto.getMedicalHistories()) {
	            MedicalHistory mh = new MedicalHistory();
	            mh.setPatient(patient);
	            mh.setIllness(mhDto.getIllness());
	            mh.setNumOfYears(mhDto.getNumOfYears());
	            mh.setCurrentMedication(mhDto.getCurrentMedication());
	           
	            savedHistories.add(medicalHistoryRepository.save(mh));
	        }
	    }
	    return new PatientDto(patient, savedHistories);
	}
	
	
	
	//retrieving  Patient and their medical history by patient ID
	public PatientDto getPatient(int patientId) {
	    Patient patient = patientRepository.findById(patientId)
	        .orElseThrow(() -> new RuntimeException("Patient not found"));

	    List<MedicalHistory> medicalHistories = medicalHistoryRepository.getByPatientId(patientId);

	    return new PatientDto(patient, medicalHistories);
	  
	}


	
}
	

    
	
