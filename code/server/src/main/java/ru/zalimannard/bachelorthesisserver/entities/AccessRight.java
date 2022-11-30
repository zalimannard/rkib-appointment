package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record AccessRight(int id, String name) implements Entity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessRight that = (AccessRight) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
