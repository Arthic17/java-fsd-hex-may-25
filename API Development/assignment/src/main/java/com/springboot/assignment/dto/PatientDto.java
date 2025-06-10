package com.springboot.assignment.dto;

import java.util.ArrayList;
import java.util.List;

import com.springboot.assignment.model.MedicalHistory;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.User;

public class PatientDto {

	private int id;
	private String name;
	private int age;
	private User user;
	private List<MedicalHistoryDto> medicalHistories;
	
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<MedicalHistoryDto> getMedicalHistories() {
		return medicalHistories;
	}

	public void setMedicalHistories(List<MedicalHistoryDto> medicalHistories) {
		this.medicalHistories = medicalHistories;
	}

	public PatientDto(Patient patient, List<MedicalHistory> medicalHistories) {
		
		//check patient is null or not 
		if (patient != null) {
	        this.id = patient.getId();
	        this.name = patient.getName();
	        this.age = patient.getAge();
	        this.user = patient.getUser();
		 }
		 
		//check history is null or not
		 if (medicalHistories != null) {
			    List<MedicalHistoryDto> dtos = new ArrayList<>();
			    for (MedicalHistory mh : medicalHistories) {
			        MedicalHistoryDto dto = new MedicalHistoryDto();
			        dto.setIllness(mh.getIllness());
			        dto.setNumOfYears(mh.getNumOfYears());
			        dto.setCurrentMedication(mh.getCurrentMedication());
			        dtos.add(dto);
			    }
			    this.medicalHistories = dtos;
			}

		
	}
	}
