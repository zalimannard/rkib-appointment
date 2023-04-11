package ru.zalimannard.rkibappointmentbackend.schema.application.status;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.type.ApplicationStatusType;

@Entity
@Table(name = "application_statuses")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type_id", nullable = false)
    @NotNull(message = "Не указан тип статуса")
    private ApplicationStatusType type;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Не указано название статуса")
    private String name;

}