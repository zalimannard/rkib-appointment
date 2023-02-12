package ru.zalimannard.bachelorthesisserver.institution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InstitutionDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

}
