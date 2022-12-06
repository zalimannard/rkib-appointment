package ru.zalimannard.bachelorthesisserver.application.status.type;

import java.util.Objects;

public record ApplicationStatusType(int id, String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationStatusType applicationStatusType = (ApplicationStatusType) o;
        return id == applicationStatusType.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
