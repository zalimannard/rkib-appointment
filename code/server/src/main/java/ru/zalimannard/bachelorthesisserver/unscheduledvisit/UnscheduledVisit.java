package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import java.sql.Timestamp;
import java.util.Objects;

public record UnscheduledVisit(int id, int employeeId, int serviceId, int visitId, Timestamp timeStamp,
                               String commentary) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnscheduledVisit that = (UnscheduledVisit) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
