package ru.zalimannard.bachelorthesisserver.schema.schedule;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.application.Application;
import ru.zalimannard.bachelorthesisserver.schema.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.schema.favor.Favor;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatus;

import java.util.Date;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "favor_id", nullable = false)
    private Favor favor;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ScheduleStatus status;

    @Column(name = "appointment_timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentTimestamp;

    @Column(name = "commentary")
    private String commentary;

}
