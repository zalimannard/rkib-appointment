package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.Employee;

import java.util.List;

@Validated
public interface PersonService {

    PersonResponseDto create(@NotNull @Valid PersonRequestDto personDto);

    Person createEntity(@NotNull Person person);


    PersonResponseDto read(@NotNull String id);

    Person readEntity(@NotNull String id);

    PersonResponseDto readMe();

    Person readMeEntity();

    Person readEntityByUsername(String username);

    List<PersonResponseDto> readAll();

    List<Person> readAllEntities();


    PersonResponseDto update(@NotNull String id, @NotNull @Valid PersonRequestDto personDto);

    Person updateEntity(@NotNull Person person);


    void delete(@NotNull String id);

}
