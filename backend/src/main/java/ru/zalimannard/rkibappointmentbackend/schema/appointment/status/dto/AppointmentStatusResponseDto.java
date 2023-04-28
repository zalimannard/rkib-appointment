package ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.type.AppointmentStatusType;

@Value
@Builder(toBuilder = true)
public class AppointmentStatusResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("type")
    AppointmentStatusType type;

    @JsonProperty("name")
    String name;

}
