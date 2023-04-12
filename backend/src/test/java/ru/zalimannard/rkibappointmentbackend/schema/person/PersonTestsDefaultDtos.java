package ru.zalimannard.rkibappointmentbackend.schema.person;

import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public abstract class PersonTestsDefaultDtos {

    public static final PersonDto defaultPerson = new PersonDto(
            null,
            "username",
            "password",
            "Иванов",
            "Иван",
            "Иванович",
            PersonGender.MALE,
            "0123456789",
            null,
            "Россия, г.Тверь",
            "Рабочий",
            List.of(PersonRole.PATIENT)
    );

}
