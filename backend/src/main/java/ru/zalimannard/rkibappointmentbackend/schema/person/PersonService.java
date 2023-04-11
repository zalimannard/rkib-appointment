package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.List;

public interface PersonService {

    PersonDto create(@Valid PersonDto personDto);

    Person createEntity(@Valid Person person);


    PersonDto read(@NotBlank String id);

    Person readEntity(@NotNull String id);

    Person readEntityByUsername(@NotBlank String username);

    Person readEntityByEncodedPassword(@NotBlank String encodedPassword);


    List<PersonDto> search(PersonDto filterDto, Date beginBirthdate, Date endBirthdate, String[] sortBy,
                           @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Person> searchEntities(Person filter, Date beginBirthdate, Date endBirthdate, String[] sortBy,
                                @Positive int pageSize, @PositiveOrZero int pageNumber);

    List<Person> searchEntities(Person filter, Date beginBirthdate, Date endBirthdate,
                                @Positive int pageSize, @PositiveOrZero int pageNumber);


    PersonDto update(String id, @Valid PersonDto personDto);

    Person updateEntity(@NotBlank String id, @Valid Person person);


    void delete(@NotBlank String id);
}
