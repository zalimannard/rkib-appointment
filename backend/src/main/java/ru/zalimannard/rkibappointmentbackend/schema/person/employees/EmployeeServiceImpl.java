package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.role.EmployeeRole;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;

    private final PersonService personService;

    @Value("${application.default.adminEmail}")
    private String defaultAdminUsername;
    @Value("${application.default.adminPassword}")
    private String defaultAdminPassword;

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto employeeDto) {
        Person person = personService.readEntity(employeeDto.getPersonId());
        Employee employeeToCreate = mapper.toEntity(employeeDto, person);
        Employee createdEmployee = createEntity(employeeToCreate);
        return mapper.toDto(createdEmployee);
    }

    @Override
    public Employee createEntity(Employee employee) {
        try {
            return repository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-01", "Конфликт при добавлении Employee в базу данных", e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDto read(String id) {
        Employee employee = readEntity(id);
        return mapper.toDto(employee);
    }

    @Override
    public EmployeeResponseDto readMe() {
        Employee employee = readMeEntity();
        return mapper.toDto(employee);
    }

    @Override
    public Employee readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("sms-02", "Не найден Employee с id=" + id, null));
    }

    @Override
    public Employee readMeEntity() {
        String currentUsername = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Person person = personService.readEntityByUsername(currentUsername);
        return repository.findByPerson(person)
                .orElseThrow(() -> new NotFoundException("sms-06", "Не найден Employee с email=" + currentUsername, null));
    }

    @Override
    public EmployeeResponseDto update(String id, EmployeeRequestDto employeeDto) {
        Person person = personService.readEntity(employeeDto.getPersonId());
        Employee employeeToUpdate = mapper.toEntity(employeeDto, person);
        employeeToUpdate.setId(id);
        Employee updatedEmployee = updateEntity(employeeToUpdate);
        return mapper.toDto(updatedEmployee);
    }

    @Override
    public Employee updateEntity(Employee employee) {
        try {
            read(employee.getId());
            return repository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-03", "Конфликт при обновлении Employee в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Employee employee = readEntity(id);
            repository.delete(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-04", "Конфликт при удалении Employee из базы данных", e.getMessage());
        }
    }

    @PostConstruct
    public void initDefaultAdmin() {
        Person existingPerson = null;
        try {
            existingPerson = personService.readEntityByUsername(defaultAdminUsername);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        if (existingPerson == null) {
            String adminTextField = "ADMIN";
            Person adminToCreate = Person.builder()
                    .email(defaultAdminUsername)
                    .password(defaultAdminPassword)
                    .lastName(adminTextField)
                    .firstName(adminTextField)
                    .patronymic(adminTextField)
                    .build();
            Person createdAdminPerson = personService.createEntity(adminToCreate);

            Employee adminEmployee = Employee.builder()
                    .person(createdAdminPerson)
                    .roles(new HashSet<>(Set.of(EmployeeRole.ADMIN)))
                    .build();
            createEntity(adminEmployee);
        } else {
            log.info("Человек с email=" + defaultAdminUsername + " уже существует");
        }
    }

}
