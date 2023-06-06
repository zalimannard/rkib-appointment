package ru.zalimannard.api.schedule;

import lombok.Builder;
import lombok.Value;
import ru.zalimannard.api.appointment.AppointmentResponse;
import ru.zalimannard.api.person.employee.EmployeeResponse;
import ru.zalimannard.api.procedure.ProcedureResponse;
import ru.zalimannard.api.schedule.status.ScheduleStatusResponse;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class ScheduleResponse {

    String id;

    EmployeeResponse doctor;

    ProcedureResponse procedure;

    AppointmentResponse appointment;

    ScheduleStatusResponse status;

    Date appointmentTime;

    String commentary;

}
