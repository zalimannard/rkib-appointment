package ru.zalimannard.rkibappointmentbackend.schema.institution;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "institutions")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Не указано название учреждения")
    private String name;

}