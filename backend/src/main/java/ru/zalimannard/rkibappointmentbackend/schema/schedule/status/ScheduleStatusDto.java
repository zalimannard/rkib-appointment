package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type.ScheduleStatusType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleStatusDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("type")
    private ScheduleStatusType type;

    @JsonProperty("name")
    private String name;

}
