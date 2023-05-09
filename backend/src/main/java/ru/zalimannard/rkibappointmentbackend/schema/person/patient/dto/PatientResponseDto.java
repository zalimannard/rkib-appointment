package ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;

import java.time.LocalDate;
import java.util.Date;

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
    PersonResponseDto person;

}
