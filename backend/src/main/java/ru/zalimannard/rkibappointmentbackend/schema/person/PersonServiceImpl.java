package ru.zalimannard.rkibappointmentbackend.schema.person;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.Utils;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper mapper;
    private final PersonRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public PersonDto create(PersonDto personDto) {
        Person request = mapper.toEntity(personDto);

        Person response = createEntity(request);

        return mapper.toDto(response);
    }

    @Override
    public Person createEntity(Person person) {
        try {
            return saveToDatabase(person);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pes-01", "person", e.getLocalizedMessage());
        }
    }


    @Override
    public PersonDto read(String id) {
        Person response = readEntity(id);

        return mapper.toDto(response);
    }

    @Override
    public Person readEntity(String id) {
        Optional<Person> personOpt = repository.findById(id);
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            return person;
        } else {
            throw new NotFoundException("pes-02", "id", id);
        }
    }

    @Override
    public Person readEntityByUsername(String username) {
        return repository.getPersonByUsername(username);
    }

    @Override
    public Person readEntityByEncodedPassword(String encodedPassword) {
        return repository.getPersonByPassword(encodedPassword);
    }


    @Override
    public List<PersonDto> search(PersonDto filterDto, Date beginBirthdate, Date endBirthdate, String[] sortBy,
                                  int pageSize, int pageNumber) {
        Person filter = mapper.toEntity(filterDto);

        List<Person> response = searchEntities(filter, beginBirthdate, endBirthdate, sortBy, pageSize, pageNumber);

        return mapper.toDtoList(response);
    }

    @Override
    public List<Person> searchEntities(Person filter, Date beginBirthdate, Date endBirthdate, String[] sortBy,
                                       int pageSize, int pageNumber) {
        List<Sort.Order> orders = Utils.ordersByStringArray(sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        return repository.search(
                filter.getUsername(),
                filter.getLastName(),
                filter.getFirstName(),
                filter.getPatronymic(),
                filter.getPhoneNumber(),
                filter.getAddress(),
                filter.getOccupation(),
                filter.getGender(),
                beginBirthdate,
                endBirthdate,
                pageable);
    }

    @Override
    public List<Person> searchEntities(Person filter, Date beginBirthdate, Date endBirthdate,
                                       int pageSize, int pageNumber) {
        String sortByFromProperties = "${application.constant.defaultSort}";
        String[] sortBy;
        try {
            sortBy = sortByFromProperties.split(",");
        } catch (NullPointerException e) {
            throw new NotFoundException("pes-03", "defaultSort", null);
        }
        return searchEntities(filter, beginBirthdate, endBirthdate, sortBy, pageSize, pageNumber);
    }


    @Override
    public PersonDto update(String id, PersonDto personDto) {
        Person request = mapper.toEntity(personDto);

        Person response = updateEntity(id, request);

        return mapper.toDto(response);
    }

    @Override
    public Person updateEntity(String id, Person person) {
        if (repository.existsById(id)) {
            person.setId(id);
            return saveToDatabase(person);
        } else {
            throw new NotFoundException("pes-04", "id", id);
        }
    }


    @Override
    public void delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("pes-05", "id", id);
        }
    }

    private Person saveToDatabase(Person person) {
        try {
            String textToEncode = person.getUsername() + ":" + person.getPassword();
            String encodedPassword = passwordEncoder.encode(textToEncode);
            person.setPassword(encodedPassword);

            Person savedPerson = repository.save(person);
            savedPerson.setPassword(null);
            return savedPerson;
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pes-06", "person", e.getLocalizedMessage());
        }

    }
}
