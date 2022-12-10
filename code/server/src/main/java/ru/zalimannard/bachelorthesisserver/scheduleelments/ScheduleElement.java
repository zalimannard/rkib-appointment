package ru.zalimannard.bachelorthesisserver.scheduleelments;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;
import ru.zalimannard.bachelorthesisserver.service.Service;

import java.sql.Timestamp;

@Entity
@Table(name = "schedule_elements")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ScheduleElementStatus status;

    @NotNull
    @Column(name = "appointment_timestamp", nullable = false)
    private Timestamp appointmentTimestamp;
}
