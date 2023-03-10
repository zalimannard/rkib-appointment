package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.schedule.status.type.ScheduleStatusType;

@Data
@Builder
public class ScheduleStatusDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private ScheduleStatusType type;

    @JsonProperty("name")
    private String name;
}
