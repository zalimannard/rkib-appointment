package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

@Entity
@Table(name = "оchedule_statuses")
@Data
public class ScheduleStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type_id", nullable = false)
    @NotNull(message = "Не указан тип статуса")
    private ScheduleStatusType type;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Не указано название статуса")
    private String name;

}