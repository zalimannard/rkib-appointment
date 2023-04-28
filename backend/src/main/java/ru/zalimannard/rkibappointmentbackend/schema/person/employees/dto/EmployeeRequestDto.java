package ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.role.EmployeeRole;

import java.util.Set;

@Value
@Builder(toBuilder = true)
public class EmployeeRequestDto {

    @JsonProperty("personId")
    @NotBlank(message = "Не указан человек")
    String personId;

    @JsonProperty("roles")
    @NotEmpty(message = "Не указаны роли")
    Set<EmployeeRole> roles;

}
