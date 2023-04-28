package ru.zalimannard.rkibappointmentbackend.schema.appointment.status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, String> {

}
