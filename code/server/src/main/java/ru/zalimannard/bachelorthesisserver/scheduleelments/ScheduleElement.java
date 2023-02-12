package ru.zalimannard.bachelorthesisserver.scheduleelments;

import jakarta.persistence.*;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;
import ru.zalimannard.bachelorthesisserver.favor.Favor;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "schedule_elements")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Favor favor;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ScheduleElementStatus status;

    @Column(name = "appointment_timestamp", nullable = false)
    private Timestamp appointmentTimestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleElement that = (ScheduleElement) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
