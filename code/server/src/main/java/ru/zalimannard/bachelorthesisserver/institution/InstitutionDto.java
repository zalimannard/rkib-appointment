package ru.zalimannard.bachelorthesisserver.institution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstitutionDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
