package ru.zalimannard.api.schedule;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class ScheduleRequest {

    String doctorId;

    String procedureId;

    String appointmentId;

    String statusId;

    Date appointmentTime;

    String commentary;

}
