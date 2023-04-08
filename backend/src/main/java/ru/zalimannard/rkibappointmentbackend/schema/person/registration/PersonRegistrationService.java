package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;

public interface PersonRegistrationService {

    PersonDto register(PersonRegistrationDto personDto);

}
