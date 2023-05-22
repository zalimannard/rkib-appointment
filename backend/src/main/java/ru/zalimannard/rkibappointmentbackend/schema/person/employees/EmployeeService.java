package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;

@Validated
public interface EmployeeService {

    EmployeeResponseDto create(@NotNull @Valid EmployeeRequestDto employeeDto);

    Employee createEntity(@NotNull Employee employee);


    EmployeeResponseDto read(@NotNull String id);

    EmployeeResponseDto readMe();

    Employee readEntity(@NotNull String id);

    Employee readMeEntity();


    EmployeeResponseDto update(@NotNull String id, @NotNull @Valid EmployeeRequestDto employeeDto);

    Employee updateEntity(@NotNull Employee employee);


    void delete(@NotNull String id);

}
