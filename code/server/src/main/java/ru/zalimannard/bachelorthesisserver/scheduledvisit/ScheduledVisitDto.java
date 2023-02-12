package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ScheduledVisitDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("schedule_element_id")
    private int scheduleElementId;

    @JsonProperty("application_id")
    private int applicationId;

}
