package ru.zalimannard.bachelorthesisserver.doctornote;

import java.util.Objects;

public record DoctorNote(int id, int institutionId, String preliminaryDiagnosis) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorNote that = (DoctorNote) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
