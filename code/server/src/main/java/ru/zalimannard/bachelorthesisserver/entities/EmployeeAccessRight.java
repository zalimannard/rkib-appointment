package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record EmployeeAccessRight(int id, int accessRightId, int employeeId) implements Entity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAccessRight that = (EmployeeAccessRight) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
