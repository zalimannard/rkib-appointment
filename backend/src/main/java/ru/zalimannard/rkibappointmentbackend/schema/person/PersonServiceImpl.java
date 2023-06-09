package ru.zalimannard.rkibappointmentbackend.schema.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.EmployeeMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.PatientMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonMapper mapper;
    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final PatientMapper patientMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public PersonResponseDto create(PersonRequestDto personDto) {
        Person personToCreate = mapper.toEntity(personDto);
        Person createdPerson = createEntity(personToCreate);
        return mapper.toDto(createdPerson, null, null);
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
        PatientResponseDto patientDto = patientMapper.toDto(
                person.getPatient() != null
                        ? person.getPatient().toBuilder().person(null).build()
                        : null);
        EmployeeResponseDto employeeDto = employeeMapper.toDto(
                person.getEmployee() != null
                        ? person.getEmployee().toBuilder().person(null).build()
                        : null);
        return mapper.toDto(person, patientDto, employeeDto);
    }

    @Override
    public Person readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("pes-02", "Не найден Person с id=" + id, null));
    }

    @Override
    public PersonResponseDto readMe() {
        Person person = readMeEntity();
        PatientResponseDto patientDto = patientMapper.toDto(
                person.getPatient() != null
                        ? person.getPatient().toBuilder().person(null).build()
                        : null);
        EmployeeResponseDto employeeDto = employeeMapper.toDto(
                person.getEmployee() != null
                        ? person.getEmployee().toBuilder().person(null).build()
                        : null);
        return mapper.toDto(person, patientDto, employeeDto);
    }

    @Override
    public Person readMeEntity() {
        String currentUsername = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return readEntityByUsername(currentUsername);
    }

    @Override
    public Person readEntityByUsername(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("pes-06", "Не найден Person с email=" + username, null));
    }

    @Override
    public List<PersonResponseDto> readAll() {
        List<Person> people = readAllEntities();
        List<PatientResponseDto> patientDtos = new ArrayList<>();
        List<EmployeeResponseDto> employeeDtos = new ArrayList<>();
        for (Person person : people) {
            patientDtos.add(patientMapper.toDto(
                    person.getPatient() != null
                            ? person.getPatient().toBuilder().person(null).build()
                            : null));
            employeeDtos.add(employeeMapper.toDto(
                    person.getEmployee() != null
                            ? person.getEmployee().toBuilder().person(null).build()
                            : null));
        }
        return mapper.toDtoList(people, patientDtos, employeeDtos);
    }

    @Override
    public List<Person> readAllEntities() {
        return repository.findAll();
    }

    @Override
    public PersonResponseDto update(String id, PersonRequestDto personDto) {
        Person personToUpdate = mapper.toEntity(personDto);
        personToUpdate.setId(id);
        personToUpdate = encodePersonsPassword(personToUpdate);
        Person updatedPerson = updateEntity(personToUpdate);
        PatientResponseDto patientDto = patientMapper.toDto(
                updatedPerson.getPatient() != null
                        ? updatedPerson.getPatient().toBuilder().person(null).build()
                        : null);
        EmployeeResponseDto employeeDto = employeeMapper.toDto(
                updatedPerson.getEmployee() != null
                        ? updatedPerson.getEmployee().toBuilder().person(null).build()
                        : null);
        return mapper.toDto(updatedPerson, patientDto, employeeDto);
    }

    @Override
    public Person updateEntity(Person person) {
        try {
            read(person.getId());
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
        Person person = repository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("pes-05", "Не найден Person с username=" + username, null));
        return User.builder()
                .username(person.getEmail())
                .password(person.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    private Person encodePersonsPassword(Person person) {
        return person.toBuilder()
                .password(passwordEncoder.encode(person.getPassword()))
                .build();
    }

}
