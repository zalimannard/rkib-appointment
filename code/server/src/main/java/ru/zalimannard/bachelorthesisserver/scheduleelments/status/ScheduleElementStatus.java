package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import ru.zalimannard.bachelorthesisserver.BaseEntity;

import java.util.Objects;

public record ScheduleElementStatus(int id, int typeId, String name) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleElementStatus that = (ScheduleElementStatus) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
