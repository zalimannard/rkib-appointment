package ru.zalimannard.rkibappointmentbackend;

import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;

public abstract class PersonDtoToAuthConverter {

    public static String convert(String username, String password) {
        return username + ":" + password;
    }

    public static String convert(PersonDto personDto) {
        return personDto.getUsername() + ":" + personDto.getPassword();
    }

}
