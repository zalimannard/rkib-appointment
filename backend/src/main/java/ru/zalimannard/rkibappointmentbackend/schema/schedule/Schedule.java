package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.Appointment;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.Employee;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.Procedure;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    Employee doctor;

    @ManyToOne
    @JoinColumn(name = "procedure_id", referencedColumnName = "id", nullable = false)
    Procedure procedure;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    ScheduleStatus status;

    @JsonProperty("appointmentTime")
    LocalDateTime appointmentTime;

    @JsonProperty("commentary")
    String commentary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Schedule schedule = (Schedule) o;
        return getId() != null && Objects.equals(getId(), schedule.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
