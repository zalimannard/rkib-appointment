package ru.zalimannard.bachelorthesisserver.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Builder
public class ScheduleDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("doctorId")
    @NotNull
    @Positive
    private String doctorId;

    @JsonProperty("favorId")
    @NotNull
    @Positive
    private String favorId;

    @JsonProperty("applicationId")
    @Positive
    private String applicationId;

    @JsonProperty("statusId")
    @NotNull
    @Positive
    private String statusId;

    @JsonProperty("appointmentTimestamp")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentTimestamp;

    @JsonProperty("commentary")
    @Length(min = 1)
    private String commentary;

}
