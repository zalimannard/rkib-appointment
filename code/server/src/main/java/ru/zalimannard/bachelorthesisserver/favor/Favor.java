package ru.zalimannard.bachelorthesisserver.favor;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favors")
@Data
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

}