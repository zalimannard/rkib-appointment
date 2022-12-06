package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import java.util.Objects;

public record ScheduledVisit(int id, int planElementId, int visitId) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledVisit scheduledVisit = (ScheduledVisit) o;
        return id == scheduledVisit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
