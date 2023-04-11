package ru.zalimannard.rkibappointmentbackend.schema.person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Person toEntity(PersonRegistrationDto dto);

    PersonDto toDto(Person entity);

    List<Person> toEntityList(List<PersonDto> dto);

    List<PersonDto> toDtoList(List<Person> entity);

}