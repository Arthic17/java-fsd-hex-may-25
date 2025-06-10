package com.springboot.assignment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.assignment.model.Doctor;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.PatientDoctor;



public interface PatientDoctorRepository extends JpaRepository<PatientDoctor,Integer>{
	
	//get list of all patients by doctor
	@Query("select p from PatientDoctor p where p.doctor=?1")
	List <PatientDoctor> findByDoctor(Doctor doctor);
	
	@Query("select p.patient from PatientDoctor p where p.appointmentTime=?1")
	List<Patient> getPatientsByDate(LocalDate date);
}
