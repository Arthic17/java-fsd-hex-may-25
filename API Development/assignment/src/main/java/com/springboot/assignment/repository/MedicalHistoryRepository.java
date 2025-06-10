package com.springboot.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.assignment.model.MedicalHistory;

public interface MedicalHistoryRepository  extends JpaRepository<MedicalHistory,Integer>{
	
	//getting all history by patient id
	@Query("select m from MedicalHistory m where m.patient.id = ?1")
	List<MedicalHistory> getByPatientId(@Param("patientId") int patientId);

}
