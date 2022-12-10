package ru.zalimannard.bachelorthesisserver.scheduleelments;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;
import ru.zalimannard.bachelorthesisserver.service.Service;

import java.sql.Timestamp;

@Data
@Builder
public class ScheduleElementDto {
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("doctorId")
    private Integer doctorId;

    @NotNull
    @JsonProperty("serviceId")
    private Integer serviceId;

    @NotNull
    @JsonProperty("statusId")
    private Integer statusId;

    @NotNull
    @JsonProperty("appointmentTimestamp")
    private Timestamp appointmentTimestamp;
}
