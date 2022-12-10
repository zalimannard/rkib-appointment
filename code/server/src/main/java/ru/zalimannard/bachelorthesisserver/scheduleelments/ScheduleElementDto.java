package ru.zalimannard.bachelorthesisserver.scheduleelments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ScheduleElementDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("doctorId")
    private Integer doctorId;

    @JsonProperty("serviceId")
    private Integer serviceId;

    @JsonProperty("statusId")
    private Integer statusId;

    @JsonProperty("appointmentTimestamp")
    private Timestamp appointmentTimestamp;
}
