package ru.zalimannard.rkibappointmentbackend.schema.person.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
