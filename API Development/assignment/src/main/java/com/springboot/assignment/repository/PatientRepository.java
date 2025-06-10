package com.springboot.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.assignment.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {

}
