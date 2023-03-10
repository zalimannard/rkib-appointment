package ru.zalimannard.bachelorthesisserver.schema.institution;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InstitutionDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("name")
    @NotBlank
    private String name;

}
