package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;

    private final PersonService personService;

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto staffMemberDto) {
        Person person = personService.readEntity(staffMemberDto.getPersonId());
        Employee employeeToCreate = mapper.toEntity(staffMemberDto, person);
        Employee createdEmployee = createEntity(employeeToCreate);
        return mapper.toDto(createdEmployee);
    }

    @Override
    public Employee createEntity(Employee employee) {
        try {
            return repository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-01", "Конфликт при добавлении StaffMember в базу данных", e.getMessage());
        }
    }

    @Override
    public EmployeeResponseDto read(String id) {
        Employee employee = readEntity(id);
        return mapper.toDto(employee);
    }

    @Override
    public Employee readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("sms-02", "Не найден StaffMember с id=" + id, null));
    }

    @Override
    public EmployeeResponseDto update(String id, EmployeeRequestDto staffMemberDto) {
        Person person = personService.readEntity(staffMemberDto.getPersonId());
        Employee employeeToUpdate = mapper.toEntity(staffMemberDto, person);
        employeeToUpdate.setId(id);
        Employee updatedEmployee = updateEntity(employeeToUpdate);
        return mapper.toDto(updatedEmployee);
    }

    @Override
    public Employee updateEntity(Employee employee) {
        try {
            return repository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-03", "Конфликт при обновлении StaffMember в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Employee employee = readEntity(id);
            repository.delete(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("sms-04", "Конфликт при удалении StaffMember из базы данных", e.getMessage());
        }
    }

}
