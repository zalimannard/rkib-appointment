package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record Services(int id, String name) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return id == services.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
