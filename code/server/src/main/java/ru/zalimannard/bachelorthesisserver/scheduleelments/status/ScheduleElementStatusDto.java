package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleElementStatusDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private ScheduleElementStatusType type;

    @JsonProperty("name")
    private String name;
}
