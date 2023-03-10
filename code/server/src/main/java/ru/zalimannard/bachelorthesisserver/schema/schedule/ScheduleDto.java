package ru.zalimannard.bachelorthesisserver.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ScheduleDto {
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
