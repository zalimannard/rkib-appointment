package ru.zalimannard.bachelorthesisserver.schema.patient;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "occupation")
    private String occupation;

}
