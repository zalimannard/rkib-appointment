package ru.zalimannard.bachelorthesisserver.patient;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

}
