package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import jakarta.validation.Valid;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;

public interface PersonRegistrationService {

    PersonDto register(@Valid PersonRegistrationDto personDto);

}
