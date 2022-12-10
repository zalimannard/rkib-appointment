package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import jakarta.persistence.*;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.scheduleelments.ScheduleElement;

@Entity
@Table(name = "scheduled_visits")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduledVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_element_id", nullable = false)
    private ScheduleElement scheduleElement;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;
}
