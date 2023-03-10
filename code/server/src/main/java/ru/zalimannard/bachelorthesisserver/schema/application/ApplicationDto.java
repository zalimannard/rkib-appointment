package ru.zalimannard.bachelorthesisserver.schema.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("parentApplicationId")
    private String parentApplicationId;

    @JsonProperty("patientId")
    private String patientId;

    @JsonProperty("doctorNoteId")
    private String doctorNoteId;

    @JsonProperty("statusId")
    private String statusId;

    @JsonProperty("finalDiagnosis")
    private String finalDiagnosis;

}
