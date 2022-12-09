package ru.zalimannard.bachelorthesisserver.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class PatientDto {
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @JsonProperty("middleName")
    private String middleName;

    @NotNull
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotNull
    @JsonProperty("birthdate")
    private Date birthdate;

    @NotNull
    @JsonProperty("address")
    private String address;

    @NotNull
    @JsonProperty("occupation")
    private String occupation;
}