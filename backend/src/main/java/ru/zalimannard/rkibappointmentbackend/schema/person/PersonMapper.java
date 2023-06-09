package ru.zalimannard.rkibappointmentbackend.schema.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonMapper {

    public Person toEntity(PersonRequestDto personRequestDto) {
        return Person.builder()
                .email(personRequestDto.getEmail())
                .password(personRequestDto.getPassword())
                .lastName(personRequestDto.getLastName())
                .firstName(personRequestDto.getFirstName())
                .patronymic(personRequestDto.getPatronymic())
                .build();
    }

    public PersonResponseDto toDto(Person person,
                                   PatientResponseDto patientDto,
                                   EmployeeResponseDto employeeDto) {
        if (person == null) {
            return null;
        }
        return PersonResponseDto.builder()
                .id(person.getId())
                .email(person.getEmail())
                .lastName(person.getLastName())
                .firstName(person.getFirstName())
                .patronymic(person.getPatronymic())
                .patient(patientDto)
                .employee(employeeDto)
                .build();
    }

    public List<PersonResponseDto> toDtoList(List<Person> people,
                                             List<PatientResponseDto> patientDtos,
                                             List<EmployeeResponseDto> employeeDtos) {
        List<PersonResponseDto> response = new ArrayList<>();
        for (int i = 0; i < people.size(); ++i) {
            response.add(toDto(people.get(i),
                    patientDtos != null ? patientDtos.get(i) : null,
                    employeeDtos != null ? employeeDtos.get(i) : null
            ));
        }
        return response;
    }

}
