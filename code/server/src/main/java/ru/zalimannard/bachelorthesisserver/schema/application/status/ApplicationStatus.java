package ru.zalimannard.bachelorthesisserver.schema.application.status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "application_statuses")
@Data
public class ApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type_code", nullable = false)
    private ApplicationStatusType type;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
