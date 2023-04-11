package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.application.ApplicationDto;
import ru.zalimannard.rkibappointmentbackend.schema.favor.FavorDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.ScheduleStatusDto;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "doctorId", access = JsonProperty.Access.WRITE_ONLY)
    private String doctorId;

    @JsonProperty(value = "doctor", access = JsonProperty.Access.READ_ONLY)
    private PersonDto doctor;

    @JsonProperty(value = "favorId", access = JsonProperty.Access.WRITE_ONLY)
    private String favorId;

    @JsonProperty(value = "favor", access = JsonProperty.Access.READ_ONLY)
    private FavorDto favor;

    @JsonProperty(value = "applicationId", access = JsonProperty.Access.WRITE_ONLY)
    private String applicationId;

    @JsonProperty(value = "application", access = JsonProperty.Access.READ_ONLY)
    private ApplicationDto application;

    @JsonProperty(value = "statusId", access = JsonProperty.Access.WRITE_ONLY)
    private String statusId;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private ScheduleStatusDto status;

    @JsonProperty("appointmentTimestamp")
    private Date appointmentTimestamp;

    @JsonProperty("commentary")
    private String commentary;

}
