package ru.zalimannard.bachelorthesisserver.institution;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "institutions")
@Data
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
