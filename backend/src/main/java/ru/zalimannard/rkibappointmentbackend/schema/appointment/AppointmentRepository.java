package ru.zalimannard.rkibappointmentbackend.schema.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
