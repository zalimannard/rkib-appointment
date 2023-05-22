package ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class PatientResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("phoneNumber")
    String phoneNumber;

    @JsonProperty("birthdate")
    LocalDate birthdate;

    @JsonProperty("address")
    String address;

    @JsonProperty("occupation")
    String occupation;

    @JsonProperty("person")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    PersonResponseDto person;

}
