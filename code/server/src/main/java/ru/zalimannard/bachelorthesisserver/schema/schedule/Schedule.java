package ru.zalimannard.bachelorthesisserver.schema.schedule;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.schema.favor.Favor;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.ScheduleStatus;

import java.sql.Timestamp;

@Entity
@Table(name = "schedule_elements")
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
    @JoinColumn(name = "status_id", nullable = false)
    private ScheduleStatus status;

    @Column(name = "appointment_timestamp", nullable = false)
    private Timestamp appointmentTimestamp;

    @Column(name = "comment")
    private String comment;

}
