package ru.zalimannard.rkibappointmentbackend.schema.favor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "favors")
@EqualsAndHashCode(of = "id")
public class Favor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Не указано название услуги")
    private String name;

}
