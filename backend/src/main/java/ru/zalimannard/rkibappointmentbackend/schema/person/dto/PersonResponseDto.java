package ru.zalimannard.rkibappointmentbackend.schema.person.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

@Value
@Builder(toBuilder = true)
public class PersonResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("username")
    String username;

    @JsonProperty("email")
    String email;

    @JsonProperty("lastName")
    String lastName;

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("patronymic")
    String patronymic;

    @JsonProperty("patient")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    PatientResponseDto patient;

    @JsonProperty("employee")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    EmployeeResponseDto employee;

}
