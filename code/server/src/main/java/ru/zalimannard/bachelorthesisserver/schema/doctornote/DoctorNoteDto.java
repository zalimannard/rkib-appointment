package ru.zalimannard.bachelorthesisserver.schema.doctornote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DoctorNoteDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("institutionId")
    private String institutionId;

    @JsonProperty("diagnosis")
    private String diagnosis;

}
