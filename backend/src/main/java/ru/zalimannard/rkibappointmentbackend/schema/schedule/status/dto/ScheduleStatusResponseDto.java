package ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

@Value
@Builder(toBuilder = true)
public class ScheduleStatusResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("type")
    ScheduleStatusType type;

    @JsonProperty("name")
    String name;

}
