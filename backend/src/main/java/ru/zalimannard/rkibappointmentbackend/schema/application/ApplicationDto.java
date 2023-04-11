package ru.zalimannard.rkibappointmentbackend.schema.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.ApplicationStatusDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.InstitutionDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("patient")
    private Person patient;

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
