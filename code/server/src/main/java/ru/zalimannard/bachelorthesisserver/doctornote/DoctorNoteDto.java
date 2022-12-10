package ru.zalimannard.bachelorthesisserver.doctornote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorNoteDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("institutionId")
    private Integer institutionId;

    @JsonProperty("diagnosis")
    private String diagnosis;
}
