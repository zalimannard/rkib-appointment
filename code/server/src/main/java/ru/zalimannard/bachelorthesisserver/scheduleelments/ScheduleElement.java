package ru.zalimannard.bachelorthesisserver.scheduleelments;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.favor.Favor;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;

import java.sql.Timestamp;

@Entity
@Table(name = "schedule_elements")
@Data
public class ScheduleElement {
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
    @JoinColumn(name = "status_id", nullable = false)
    private ScheduleElementStatus status;

    @Column(name = "appointment_timestamp", nullable = false)
    private Timestamp appointmentTimestamp;

}
