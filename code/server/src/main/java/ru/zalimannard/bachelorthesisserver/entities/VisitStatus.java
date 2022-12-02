package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record VisitStatus(int id, int typeId, String name) implements Entity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitStatus that = (VisitStatus) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
