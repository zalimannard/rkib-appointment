package ru.zalimannard.bachelorthesisserver.application.status;

import java.util.Objects;

public record ApplicationStatus(int id, int typeId, String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationStatus that = (ApplicationStatus) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
