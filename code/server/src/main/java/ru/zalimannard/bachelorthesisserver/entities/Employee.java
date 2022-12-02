package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record Employee(int id, String lastName, String firstName, String middleName, String login,
                       String passwordHash, String passwordSalt) implements BaseEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
