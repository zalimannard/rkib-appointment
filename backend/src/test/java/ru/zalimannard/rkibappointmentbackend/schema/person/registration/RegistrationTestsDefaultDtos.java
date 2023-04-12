package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public abstract class RegistrationTestsDefaultDtos {

    public static final PersonRegistrationDto defaultPersonRegistration = new PersonRegistrationDto(
            "username",
            "password",
            "Иванов",
            "Иван",
            "Иванович",
            PersonGender.MALE,
            "0123456789",
            Date.from(Instant.now().minusSeconds(100)),
            "Россия, г.Тверь",
            "Рабочий"
    );

    public static final PersonDto defaultCreatedPerson = new PersonDto(
            null,
            "username",
            null,
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
