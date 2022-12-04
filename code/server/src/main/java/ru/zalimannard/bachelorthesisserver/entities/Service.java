package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record Service(int id, String name) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
