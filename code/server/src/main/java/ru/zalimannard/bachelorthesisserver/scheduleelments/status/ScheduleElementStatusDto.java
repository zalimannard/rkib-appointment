package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleElementStatusDto {
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("type")
    private ScheduleElementStatusType type;

    @NotNull
    @JsonProperty("name")
    private String name;
}
