package ru.zalimannard.bachelorthesisserver.application;

import java.util.Objects;

public record Application(int id, int parentVisitId, int patientId, int serviceId, int doctorNoteId, int statusId,
                          String finalDiagnosis) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application application = (Application) o;
        return id == application.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
