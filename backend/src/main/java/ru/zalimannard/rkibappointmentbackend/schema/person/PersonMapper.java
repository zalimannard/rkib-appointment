package ru.zalimannard.rkibappointmentbackend.schema.person;

import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;

@Component
public class PersonMapper {

    public Person toEntity(PersonRequestDto personRequestDto) {
        return Person.builder()
                .username(personRequestDto.getUsername())
                .password(personRequestDto.getPassword())
                .lastName(personRequestDto.getLastName())
                .firstName(personRequestDto.getFirstName())
                .patronymic(personRequestDto.getPatronymic())
                .build();
    }

    public PersonResponseDto toDto(Person person) {
        return PersonResponseDto.builder()
                .id(person.getId())
                .username(person.getUsername())
                .lastName(person.getLastName())
                .firstName(person.getFirstName())
                .patronymic(person.getPatronymic())
                .build();
    }

}
