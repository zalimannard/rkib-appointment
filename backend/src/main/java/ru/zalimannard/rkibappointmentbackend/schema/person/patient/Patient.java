package ru.zalimannard.rkibappointmentbackend.schema.person.patient;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "patients")
@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person person;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "birthdate")
    LocalDate birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "occupation")
    private String occupation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return getId() != null && Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
