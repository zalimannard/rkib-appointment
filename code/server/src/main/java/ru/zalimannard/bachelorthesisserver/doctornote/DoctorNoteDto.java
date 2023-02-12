package ru.zalimannard.bachelorthesisserver.doctornote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DoctorNoteDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("institutionId")
    private int institutionId;

    @JsonProperty("diagnosis")
    private String diagnosis;

}
