package ru.zalimannard.bachelorthesisserver.schema.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ApplicationDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("patientId")
    @NotNull
    private String patientId;

    @JsonProperty("sendingInstitutionId")
    private String sendingInstitutionId;

    @JsonProperty("statusId")
    @NotNull
    private String statusId;

    @JsonProperty("doctorNote")
    @Length(min = 1)
    private String doctorNote;

    @JsonProperty("finalDiagnosis")
    @Length(min = 1)
    private String finalDiagnosis;

    @JsonProperty("commentary")
    @Length(min = 1)
    private String commentary;

}
