package ru.zalimannard.bachelorthesisserver.appointment.status;

import java.util.Objects;

public record AppointmentStatus(int id, int typeId, String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentStatus that = (AppointmentStatus) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
