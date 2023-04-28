package ru.zalimannard.rkibappointmentbackend.schema.appointment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.AppointmentStatus;
import ru.zalimannard.rkibappointmentbackend.schema.institution.Institution;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.Patient;

import java.util.Objects;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "institution_id", referencedColumnName = "id")
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private AppointmentStatus status;

    @Column(name = "doctorNote")
    private String doctorNote;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "commentary")
    private String commentary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Appointment that = (Appointment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
