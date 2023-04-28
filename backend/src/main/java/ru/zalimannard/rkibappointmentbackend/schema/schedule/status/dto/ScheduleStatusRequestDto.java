package ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

@Value
@Builder(toBuilder = true)
public class ScheduleStatusRequestDto {

    @JsonProperty("type")
    @NotNull(message = "Не указан тип статуса элемента графика")
    ScheduleStatusType type;

    @JsonProperty("name")
    @NotBlank(message = "Не указано название статуса элемента графика")
    String name;

}
