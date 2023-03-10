package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.type.ScheduleStatusType;

@Data
public class ScheduleStatusDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("type")
    @NotNull
    private ScheduleStatusType type;

    @JsonProperty("name")
    @NotBlank
    private String name;

}
