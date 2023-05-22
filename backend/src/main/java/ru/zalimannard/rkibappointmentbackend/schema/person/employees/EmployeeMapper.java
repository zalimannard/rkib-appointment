package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final PersonMapper personMapper;

    public Employee toEntity(EmployeeRequestDto patientRequestDto,
                             Person person) {
        return Employee.builder()
                .person(person)
                .roles(new HashSet<>(patientRequestDto.getRoles()))
                .build();
    }

    public EmployeeResponseDto toDto(Employee patient) {
        if (patient == null) {
            return null;
        }
        return EmployeeResponseDto.builder()
                .id(patient.getId())
                .person(personMapper.toDto(patient.getPerson(), null, null))
                .roles(new HashSet<>(patient.getRoles()))
                .build();
    }

}
