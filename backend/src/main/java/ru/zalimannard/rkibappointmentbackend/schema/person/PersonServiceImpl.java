package ru.zalimannard.rkibappointmentbackend.schema.person;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;

@Service
@RequiredArgsConstructor
@Validated
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public PersonDto create(PersonDto personDto) {
        Person personRequest = personMapper.toEntity(personDto);

        Person personResponse = createEntity(personRequest);

        return personMapper.toDto(personResponse);
    }

    @Override
    public Person createEntity(Person person) {
        return saveToDatabase(person);
    }

    @Override
    public PersonDto read(String id) {
        Person personResponse = readEntity(id);

        return personMapper.toDto(personResponse);
    }

    @Override
    public Person readEntity(String id) {
        return personRepository.getReferenceById(id);
    }

    public Person saveToDatabase(Person person) {
        try {
            return personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException();
        }
    }

}
