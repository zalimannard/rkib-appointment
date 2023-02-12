package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScheduledVisitDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("schedule_element_id")
    private Integer scheduleElementId;

    @JsonProperty("application_id")
    private Integer applicationId;

}
