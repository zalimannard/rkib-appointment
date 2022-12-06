package ru.zalimannard.bachelorthesisserver.appointment;

import java.util.Objects;

public record Appointment(int id, int parentVisitId, int patientId, int serviceId, int doctorNoteId, int statusId,
                          String finalDiagnosis) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment appointment = (Appointment) o;
        return id == appointment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
