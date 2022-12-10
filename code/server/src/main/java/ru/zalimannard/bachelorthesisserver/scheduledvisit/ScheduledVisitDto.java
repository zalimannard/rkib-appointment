package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduledVisitDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("schedule_element_id")
    private Integer scheduleElementId;

    @JsonProperty("application_id")
    private Integer applicationId;
}
