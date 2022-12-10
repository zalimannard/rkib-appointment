package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatusType;

import java.util.Objects;

@Entity
@Table(name = "schedule_element_statuses")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleElementStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "type_code", nullable = false)
    private ScheduleElementStatusType type;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleElementStatus that = (ScheduleElementStatus) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}