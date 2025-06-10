package com.springboot.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.assignment.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{

}
