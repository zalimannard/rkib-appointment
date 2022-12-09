package ru.zalimannard.bachelorthesisserver.doctornote;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorNoteDto {
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("institutionId")
    private Integer institutionId;

    @NotNull
    @JsonProperty("diagnosis")
    private String diagnosis;
}
