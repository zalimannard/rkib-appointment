package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonMapper mapper;
    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Value("${application.default.adminUsername}")
    private String defaultAdminUsername;
    @Value("${application.default.adminPassword}")
    private String defaultAdminPassword;

    @Override
    public PersonResponseDto create(PersonRequestDto personDto) {
        Person personToCreate = mapper.toEntity(personDto);
        Person createdPerson = createEntity(personToCreate);
        return mapper.toDto(createdPerson);
    }

    @Override
    public Person createEntity(Person person) {
        try {
            Person personToSave = encodePersonsPassword(person);
            return repository.save(personToSave);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pes-01", "Конфликт при добавлении Person в базу данных", e.getMessage());
        }
    }

    @Override
    public PersonResponseDto read(String id) {
        Person person = readEntity(id);
        return mapper.toDto(person);
    }

    @Override
    public Person readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("pes-02", "Не найден Person с id=" + id, null));
    }

    @Override
    public PersonResponseDto update(String id, PersonRequestDto personDto) {
        Person personToUpdate = mapper.toEntity(personDto);
        personToUpdate.setId(id);
        personToUpdate = encodePersonsPassword(personToUpdate);
        Person updatedPerson = updateEntity(personToUpdate);
        return mapper.toDto(updatedPerson);
    }

    @Override
    public Person updateEntity(Person person) {
        try {
            return repository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pes-03", "Конфликт при обновлении Person в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Person person = readEntity(id);
            repository.delete(person);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pes-04", "Конфликт при удалении Person из базы данных", e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person person = repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("pes-05", "Не найден Person с username=" + username, null));
        return User.builder()
                .username(person.getUsername())
                .password(person.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    @PostConstruct
    public void initDefaultAdmin() {
        Optional<Person> existingPerson = repository.findByUsername(defaultAdminUsername);
        if (existingPerson.isEmpty()) {
            String adminTextField = "ADMIN";
            Person adminToCreate = Person.builder()
                    .username(defaultAdminUsername)
                    .password(defaultAdminPassword)
                    .lastName(adminTextField)
                    .firstName(adminTextField)
                    .patronymic(adminTextField)
                    .build();
            adminToCreate = encodePersonsPassword(adminToCreate);
            repository.save(adminToCreate);
        } else {
            // TODO: Сделать разделение на пациентов и сотрудников
            log.info("Человек с username=" + defaultAdminUsername + " уже существует");
        }
    }

    private Person encodePersonsPassword(Person person) {
        return person.toBuilder()
                .password(passwordEncoder.encode(person.getPassword()))
                .build();
    }

}
