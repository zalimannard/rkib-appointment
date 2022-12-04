package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record EmployeesRole(int id, int roleId, int employeeId) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeesRole that = (EmployeesRole) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
