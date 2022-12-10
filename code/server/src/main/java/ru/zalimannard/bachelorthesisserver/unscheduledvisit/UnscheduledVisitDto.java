package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UnscheduledVisitDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("doctorId")
    private Integer doctorId;

    @JsonProperty("serviceId")
    private Integer serviceId;

    @JsonProperty("applicationId")
    private Integer applicationId;

    @JsonProperty("appointmentTimestamp")
    private Timestamp appointmentTimestamp;

    @JsonProperty("comment")
    private String comment;
}
