package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "schedule_element_statuses")
@Data
public class ScheduleElementStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_code", nullable = false)
    private ScheduleElementStatusType type;

    @Column(name = "name", nullable = false)
    private String name;

}