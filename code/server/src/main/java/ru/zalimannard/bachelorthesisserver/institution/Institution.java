package ru.zalimannard.bachelorthesisserver.institution;

import ru.zalimannard.bachelorthesisserver.BaseEntity;

import java.util.Objects;

public record Institution(int id, String name) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institution that = (Institution) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
