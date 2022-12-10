package ru.zalimannard.bachelorthesisserver.patient;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "patients")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "occupation", nullable = false)
    private String occupation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
