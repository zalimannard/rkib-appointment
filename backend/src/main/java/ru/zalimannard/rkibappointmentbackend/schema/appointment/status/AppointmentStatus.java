package ru.zalimannard.rkibappointmentbackend.schema.appointment.status;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.type.AppointmentStatusType;

import java.util.Objects;

@Entity
@Table(name = "appointment_statuses")
@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class AppointmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type", nullable = false)
    private AppointmentStatusType type;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppointmentStatus appointmentStatus = (AppointmentStatus) o;
        return getId() != null && Objects.equals(getId(), appointmentStatus.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
