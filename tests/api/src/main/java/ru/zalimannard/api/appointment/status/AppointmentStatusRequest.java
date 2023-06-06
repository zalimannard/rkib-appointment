package ru.zalimannard.api.appointment.status;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AppointmentStatusRequest {

    String type;

    String name;

}
