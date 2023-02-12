package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "schedule_element_statuses")
@Data
public class ScheduleElementStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type_code", nullable = false)
    private ScheduleElementStatusType type;

    @Column(name = "name", nullable = false)
    private String name;

}