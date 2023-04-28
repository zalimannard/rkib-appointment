package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
