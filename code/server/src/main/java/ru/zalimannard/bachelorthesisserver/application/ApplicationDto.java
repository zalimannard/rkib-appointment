package ru.zalimannard.bachelorthesisserver.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("parentApplicationId")
    private Integer parentApplicationId;

    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("doctorNoteId")
    private Integer doctorNoteId;

    @JsonProperty("statusId")
    private Integer statusId;

    @JsonProperty("finalDiagnosis")
    private String finalDiagnosis;
}
