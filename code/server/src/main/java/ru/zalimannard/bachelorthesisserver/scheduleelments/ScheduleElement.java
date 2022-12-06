package ru.zalimannard.bachelorthesisserver.scheduleelments;

import ru.zalimannard.bachelorthesisserver.BaseEntity;

import java.sql.Timestamp;
import java.util.Objects;

public record ScheduleElement(int id, int employeeId, int serviceId, int statusId,
                              Timestamp timeStamp) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleElement that = (ScheduleElement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
