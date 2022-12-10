package ru.zalimannard.bachelorthesisserver.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;
}
