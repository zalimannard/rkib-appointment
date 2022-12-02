package ru.zalimannard.bachelorthesisserver.entities;

import java.util.Objects;

public record Visit(int id, int parentVisitId, int patientId, int serviceId, int doctorNoteId, int statusId,
                    String finalDiagnosis) implements Entity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
