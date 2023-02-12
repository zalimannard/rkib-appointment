package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElement;

@Entity
@Table(name = "scheduled_visits")
@Data
public class ScheduledVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "schedule_element_id", nullable = false)
    private ScheduleElement scheduleElement;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

}
