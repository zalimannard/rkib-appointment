package ru.zalimannard.bachelorthesisserver.entities;

import java.sql.Timestamp;
import java.util.Objects;

public record PlanElement(int id, int employeeId, int serviceId, int statusId, Timestamp timeStamp) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanElement that = (PlanElement) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
