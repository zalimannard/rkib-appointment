package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

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
    @Transactional
    public Person readEntity(String id) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            person.setPassword(null);
            return person;
        } else {
            throw new NotFoundException("p-001", "id", id);
        }
    }

    @Override
    public Person readEntityByUsername(String username) {
        return personRepository.getPersonByUsername(username);
    }

    @Override
    public Person readEntityByEncodedPassword(String encodedPassword) {
        return personRepository.getPersonByPassword(encodedPassword);
    }

    private Person saveToDatabase(Person person) {
        try {
            String textToEncode = person.getUsername() + ":" + person.getPassword();
            String encodedPassword = passwordEncoder.encode(textToEncode);
            Person personToSave = person.toBuilder().password(encodedPassword).build();

            Person savedPerson = personRepository.save(personToSave);
            savedPerson.setPassword(null);
            return savedPerson;
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("p-002", "person", e.getLocalizedMessage());
        }
    }

}
