package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.application.ApplicationDto;
import ru.zalimannard.rkibappointmentbackend.schema.favor.FavorDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatusDto;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("doctorId")
    private String doctorId;

    @JsonProperty("doctor")
    private PersonDto doctor;

    @JsonProperty("favorId")
    private String favorId;

    @JsonProperty("favor")
    private FavorDto favor;

    @JsonProperty("applicationId")
    private String applicationId;

    @JsonProperty("application")
    private ApplicationDto application;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("status")
    private ScheduleStatusDto status;

    @JsonProperty("appointmentTimestamp")
    private Date appointmentTimestamp;

    @JsonProperty("commentary")
    private String commentary;

}
