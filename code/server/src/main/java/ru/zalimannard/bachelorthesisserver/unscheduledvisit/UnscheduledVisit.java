package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import jakarta.persistence.*;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.service.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "unscheduled_visits")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class UnscheduledVisit {
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
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(name = "appointment_timestamp", nullable = false)
    private Timestamp appointmentTimestamp;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnscheduledVisit that = (UnscheduledVisit) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
