package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByPerson(Person person);

}
