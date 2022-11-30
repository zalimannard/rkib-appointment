package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.entities.Patient;
import ru.zalimannard.bachelorthesisserver.repositories.EmployeeRepository;
import ru.zalimannard.bachelorthesisserver.repositories.PatientRepository;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return employeeRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        employeeRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        employeeRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        employeeRepository.delete(id);
    }
}
