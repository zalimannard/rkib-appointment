package ru.zalimannard.bachelorthesisserver.institution;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "institutions")
@Data
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

}
