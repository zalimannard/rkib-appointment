package ru.zalimannard.bachelorthesisserver.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("parentApplicationId")
    private int parentApplicationId;

    @JsonProperty("patientId")
    private int patientId;

    @JsonProperty("doctorNoteId")
    private int doctorNoteId;

    @JsonProperty("statusId")
    private int statusId;

    @JsonProperty("finalDiagnosis")
    private String finalDiagnosis;

}
