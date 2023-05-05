package ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class PatientResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("phoneNumber")
    String phoneNumber;

    @JsonProperty("birthdate")
    Date birthdate;

    @JsonProperty("address")
    String address;

    @JsonProperty("occupation")
    String occupation;

    @JsonProperty("person")
    PersonResponseDto person;

}
