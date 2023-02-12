package ru.zalimannard.bachelorthesisserver.favor;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favors")
@Data
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

}
