package ru.zalimannard.bachelorthesisserver.patient;

import java.sql.Date;
import java.util.Objects;

public record Patient(int id, String lastName, String firstName, String middleName, String phoneNumber, Date birthDate,
                      String address, String job) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
