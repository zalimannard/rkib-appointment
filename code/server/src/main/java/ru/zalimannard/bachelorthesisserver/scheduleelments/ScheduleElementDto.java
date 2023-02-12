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
    private int doctorId;

    @JsonProperty("serviceId")
    private int serviceId;

    @JsonProperty("statusId")
    private int statusId;

    @JsonProperty("appointmentTimestamp")
    private Timestamp appointmentTimestamp;
}
