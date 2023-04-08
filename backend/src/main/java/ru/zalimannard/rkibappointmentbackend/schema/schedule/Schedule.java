package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.zalimannard.rkibappointmentbackend.schema.application.Application;
import ru.zalimannard.rkibappointmentbackend.schema.favor.Favor;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatus;

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
    @NotNull(message = "Не указан врач")
    private Person doctor;

    @ManyToOne
    @JoinColumn(name = "favor_id", nullable = false)
    @NotNull(message = "Не указана услуга")
    private Favor favor;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull(message = "Не указан статус обращения")
    private ScheduleStatus status;

    @Column(name = "appointment_timestamp", nullable = false)
    @NotNull(message = "Не указано время приёма")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentTimestamp;

    @Column(name = "commentary")
    @Length(min = 1)
    private String commentary;

}