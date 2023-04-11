package ru.zalimannard.rkibappointmentbackend.schema.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.ApplicationStatusDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.InstitutionDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(value = "patientId", access = JsonProperty.Access.WRITE_ONLY)
    private String patientId;

    @JsonProperty(value = "patient", access = JsonProperty.Access.READ_ONLY)
    private PersonDto patient;

    @JsonProperty(value = "sendingInstitutionId", access = JsonProperty.Access.WRITE_ONLY)
    private String sendingInstitutionId;

    @JsonProperty(value = "sendingInstitution", access = JsonProperty.Access.READ_ONLY)
    private InstitutionDto sendingInstitution;

    @JsonProperty(value = "statusId", access = JsonProperty.Access.WRITE_ONLY)
    private String statusId;

    @JsonProperty(value = "status", access = JsonProperty.Access.READ_ONLY)
    private ApplicationStatusDto status;

    @JsonProperty("doctorNote")
    private String doctorNote;

    @JsonProperty("finalDiagnosis")
    private String finalDiagnosis;

    @JsonProperty("commentary")
    private String commentary;

}
