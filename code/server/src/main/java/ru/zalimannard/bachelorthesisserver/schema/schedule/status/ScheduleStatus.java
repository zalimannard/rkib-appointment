package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.type.ScheduleStatusType;

@Entity
@Table(name = "schedule_statuses")
@Data
public class ScheduleStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type_code", nullable = false)
    private ScheduleStatusType type;

    @Column(name = "name", nullable = false)
    private String name;

}