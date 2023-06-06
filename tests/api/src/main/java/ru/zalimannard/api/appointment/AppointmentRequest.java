package ru.zalimannard.api.appointment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AppointmentRequest {

    String patientId;

    String institutionId;

    String statusId;

    String doctorNote;

    String diagnosis;

    String commentary;

}