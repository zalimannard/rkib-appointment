package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record VisitType(int id, String name) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitType visitType = (VisitType) o;
        return id == visitType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
