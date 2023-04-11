package ru.zalimannard.rkibappointmentbackend.schema.favor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favors")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Favor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Не указано название услуги")
    private String name;

}
