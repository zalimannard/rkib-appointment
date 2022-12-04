package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record Scheduled(int id, int planElementId, int visitId) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheduled scheduled = (Scheduled) o;
        return id == scheduled.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
