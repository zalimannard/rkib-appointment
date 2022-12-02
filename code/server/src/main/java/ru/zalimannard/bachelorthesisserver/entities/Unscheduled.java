package ru.zalimannard.bachelorthesisserver.entities;

import java.sql.Timestamp;
import java.util.Objects;

public record Unscheduled(int id, int employeeId, int serviceId, int visitId, Timestamp timeStamp,
                          String commentary) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unscheduled that = (Unscheduled) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
