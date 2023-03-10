package ru.zalimannard.bachelorthesisserver.schema.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("lastName")
    @NotBlank
    private String lastName;

    @JsonProperty("firstName")
    @NotBlank
    private String firstName;

    @JsonProperty("patronymic")
    private String patronymic;

}
