package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface PersonService {

    PersonDto create(PersonDto personDto);

    Person createEntity(@Valid Person person);


    PersonDto read(String id);

    Person readEntity(@NotNull String id);

}
