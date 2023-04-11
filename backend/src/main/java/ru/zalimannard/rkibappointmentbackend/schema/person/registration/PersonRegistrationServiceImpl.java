package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PersonRegistrationServiceImpl implements PersonRegistrationService {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Override
    public PersonDto register(PersonRegistrationDto personDto) {
        Person personRequest = personMapper.toEntity(personDto);

        personRequest.setRoles(List.of(PersonRole.PATIENT));
        Person personResponse = personService.createEntity(personRequest);

        return personMapper.toDto(personResponse);
    }

}
