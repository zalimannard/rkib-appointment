package ru.zalimannard.rkibappointmentbackend.schema.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class ScheduleRequestDto {

    @JsonProperty("doctorId")
    @NotBlank(message = "Не указан врач")
    String doctorId;

    @JsonProperty("procedureId")
    @NotBlank(message = "Не указана процедура")
    String procedureId;

    @JsonProperty("appointmentId")
    String appointmentId;

    @JsonProperty("statusId")
    @NotBlank(message = "Не указан статус элемента расписания")
    String statusId;

    @JsonProperty("appointmentTime")
    @NotNull(message = "Не указано время приёма")
    LocalDateTime appointmentTime;

    @JsonProperty("commentary")
    @Length(min = 1)
    String commentary;

    public ScheduleRequestDto(String doctorId, String procedureId, String appointmentId, String statusId, LocalDateTime appointmentTime, String commentary) {
        this.doctorId = doctorId;
        this.procedureId = procedureId;
        this.appointmentId = appointmentId;
        this.statusId = statusId;
        this.appointmentTime = appointmentTime;
        this.commentary = commentary != null ? commentary.trim() : null;
    }

}
