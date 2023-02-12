package ru.zalimannard.bachelorthesisserver.application.status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "application_statuses")
@Data
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_code", nullable = false)
    private ApplicationStatusType type;

    @Column(name = "name", nullable = false)
    private String name;

}
