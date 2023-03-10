package ru.zalimannard.bachelorthesisserver.schema.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DoctorDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("patronymic")
    private String patronymic;

}
