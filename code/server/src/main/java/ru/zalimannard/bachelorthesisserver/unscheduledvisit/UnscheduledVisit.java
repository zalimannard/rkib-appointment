package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.favor.Favor;

import java.sql.Timestamp;

@Entity
@Table(name = "unscheduled_visits")
@Data
public class UnscheduledVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Favor favor;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(name = "appointment_timestamp", nullable = false)
    private Timestamp appointmentTimestamp;

    @Column(name = "commentary", nullable = false)
    private String comment;

}
