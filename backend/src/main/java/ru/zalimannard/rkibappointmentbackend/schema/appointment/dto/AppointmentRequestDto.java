package ru.zalimannard.rkibappointmentbackend.schema.appointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder(toBuilder = true)
public class AppointmentRequestDto {

    @JsonProperty("patientId")
    @NotBlank(message = "Не указан пациент")
    String patientId;

    @JsonProperty("institutionId")
    @NotBlank(message = "Не указано учреждение")
    String institutionId;

    @JsonProperty("statusId")
    @NotBlank(message = "Не указан статус обращения")
    String statusId;

    @JsonProperty("doctorNote")
    @Length(min = 1)
    String doctorNote;

    @JsonProperty("diagnosis")
    @Length(min = 1)
    String diagnosis;

    @JsonProperty("commentary")
    @Length(min = 1)
    String commentary;

    public AppointmentRequestDto(String patientId, String institutionId, String statusId, String doctorNote, String diagnosis, String commentary) {
        this.patientId = patientId;
        this.institutionId = institutionId;
        this.statusId = statusId;
        this.doctorNote = doctorNote != null ? doctorNote.trim() : null;
        this.diagnosis = diagnosis != null ? diagnosis.trim() : null;
        this.commentary = commentary != null ? commentary.trim() : null;
    }

}
