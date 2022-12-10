package ru.zalimannard.bachelorthesisserver.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.patient.Patient;

@Data
@Builder
public class ApplicationDto {
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("parentApplicationId")
    private Integer parentApplicationId;

    @NotNull
    @JsonProperty("patientId")
    private Integer patientId;

    @NotNull
    @JsonProperty("doctorNoteId")
    private Integer doctorNoteId;

    @NotNull
    @JsonProperty("statusId")
    private Integer statusId;

    @NotNull
    @JsonProperty("finalDiagnosis")
    private String finalDiagnosis;
}
