package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

import java.util.Objects;

@Entity
@Table(name = "schedule_statuses")
@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "type", nullable = false)
    private ScheduleStatusType type;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScheduleStatus scheduleStatus = (ScheduleStatus) o;
        return getId() != null && Objects.equals(getId(), scheduleStatus.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
