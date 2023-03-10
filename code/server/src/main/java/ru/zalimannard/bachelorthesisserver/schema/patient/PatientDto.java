package ru.zalimannard.bachelorthesisserver.schema.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

@Data
public class PatientDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("lastName")
    @NotBlank
    private String lastName;

    @JsonProperty("firstName")
    @NotBlank
    private String firstName;

    @JsonProperty("patronymic")
    @Length(min = 1)
    private String patronymic;

    @JsonProperty("phoneNumber")
    @Length(min = 1)
    private String phoneNumber;

    @JsonProperty("birthdate")
    @Past
    private Date birthdate;

    @JsonProperty("address")
    @Length(min = 1)
    private String address;

    @JsonProperty("occupation")
    @Length(min = 1)
    private String occupation;

}