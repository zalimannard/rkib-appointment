package ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.role.EmployeeRole;

import java.util.Set;

@Value
@Builder(toBuilder = true)
public class EmployeeResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("person")
    PersonResponseDto person;

    @JsonProperty("roles")
    Set<EmployeeRole> roles;

}
