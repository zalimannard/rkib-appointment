package ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.type.AppointmentStatusType;

@Value
@Builder(toBuilder = true)
public class AppointmentStatusRequestDto {

    @JsonProperty("type")
    @NotNull(message = "Не указан тип статуса обращений")
    AppointmentStatusType type;

    @JsonProperty("name")
    @NotBlank(message = "Не указано название статуса обращений")
    String name;

}
