package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.EmployeeAccessRight;
import ru.zalimannard.bachelorthesisserver.repositories.EmployeeAccessRightRepository;

import java.util.List;

@RestController
@RequestMapping("employeeAccessRight")
public class EmployeeAccessRightController {
    private final EmployeeAccessRightRepository employeeAccessRightRepository;

    public EmployeeAccessRightController(EmployeeAccessRightRepository employeeAccessRightRepository) {
        this.employeeAccessRightRepository = employeeAccessRightRepository;
    }

    @GetMapping("{id}")
    public EmployeeAccessRight get(@PathVariable int id) {
        return employeeAccessRightRepository.retrieve(id);
    }

    @GetMapping
    public List<EmployeeAccessRight> getAll() {
        return employeeAccessRightRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody EmployeeAccessRight accessRight) {
        employeeAccessRightRepository.create(accessRight);
    }

    @PutMapping
    public void put(@RequestBody EmployeeAccessRight accessRight) {
        employeeAccessRightRepository.update(accessRight);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        employeeAccessRightRepository.delete(id);
    }
}
