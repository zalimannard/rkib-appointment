package ru.zalimannard.bachelorthesisserver.doctor;

import java.util.Objects;

public record Doctor(int id, String lastName, String firstName, String middleName, String login,
                     String passwordHash, String passwordSalt) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
