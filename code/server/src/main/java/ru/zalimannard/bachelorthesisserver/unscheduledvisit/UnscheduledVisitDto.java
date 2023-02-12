package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UnscheduledVisitDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("doctorId")
    private int doctorId;

    @JsonProperty("serviceId")
    private int serviceId;

    @JsonProperty("applicationId")
    private int applicationId;

    @JsonProperty("appointmentTimestamp")
    private Timestamp appointmentTimestamp;

    @JsonProperty("comment")
    private String comment;

}
