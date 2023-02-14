package ru.zalimannard.bachelorthesisserver.scheduleelments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ScheduleElementDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("doctorId")
    private String doctorId;

    @JsonProperty("favorId")
    private String favorId;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("appointmentTimestamp")
    private Timestamp appointmentTimestamp;
}
