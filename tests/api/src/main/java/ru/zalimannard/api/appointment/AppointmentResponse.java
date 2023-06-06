package ru.zalimannard.api.appointment;

import lombok.Builder;
import lombok.Value;
import ru.zalimannard.api.appointment.status.AppointmentStatusResponse;
import ru.zalimannard.api.institution.InstitutionResponse;
import ru.zalimannard.api.person.patient.PatientResponse;

@Value
@Builder(toBuilder = true)
public class AppointmentResponse {

    String id;

    PatientResponse patient;

    InstitutionResponse institution;

    AppointmentStatusResponse status;

    String doctorNote;

    String diagnosis;

    String commentary;

}
