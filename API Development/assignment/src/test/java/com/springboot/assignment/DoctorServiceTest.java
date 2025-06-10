package com.springboot.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.assignment.model.Doctor;
import com.springboot.assignment.model.Patient;
import com.springboot.assignment.model.PatientDoctor;
import com.springboot.assignment.model.User;
import com.springboot.assignment.repository.DoctorRepository;
import com.springboot.assignment.repository.PatientDoctorRepository;
import com.springboot.assignment.repository.UserRepository;
import com.springboot.assignment.service.DoctorService;

@SpringBootTest
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientDoctorRepository patientDoctorRepository;

    private User doctorUser;
    private Doctor doctor;
    private Patient patient1;
    private PatientDoctor appointment1;

    @BeforeEach
    public void setup() {// initializing mocks
        MockitoAnnotations.openMocks(this);

        doctorUser = new User();
         doctorUser.setId(10);
        doctorUser.setUsername("doctorUser");

         doctor = new Doctor();
        doctor.setId(1);
        doctor.setUser(doctorUser);

         patient1 = new Patient();
        patient1.setId(101);
        patient1.setName("Patient One");

         appointment1 = new PatientDoctor();
          appointment1.setDoctor(doctor);
        appointment1.setPatient(patient1);
    }

    @Test
    public void testGetPatientsByDoctor() {
    	 // for valid doctor
    	when(userRepository.getByUsername("doctorUser")).thenReturn(doctorUser);
          when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(patientDoctorRepository.findByDoctor(doctor)).thenReturn(List.of(appointment1));

        List<Patient> patients = doctorService.getPatientsByDoctor(1, "doctorUser");

           assertEquals(1, patients.size());
        assertEquals(patient1, patients.get(0));
    }

    @Test
    public void testDoctorNotFound() {
    	//for non-existing doctor 
    	when(userRepository.getByUsername("doctorUser")).thenReturn(doctorUser);
        when(doctorRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> doctorService.getPatientsByDoctor(99, "doctorUser"));

        assertEquals("Doctor not found", ex.getMessage());
    }

    @Test
    public void testDoctorHasNoPatients() {
        
    	//for doctor with no patients
    	when(userRepository.getByUsername("doctorUser")).thenReturn(doctorUser);
            when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));
        when(patientDoctorRepository.findByDoctor(doctor)).thenReturn(Collections.emptyList());

          List<Patient> patients = doctorService.getPatientsByDoctor(1, "doctorUser");

        assertTrue(patients.isEmpty());
    }
}
