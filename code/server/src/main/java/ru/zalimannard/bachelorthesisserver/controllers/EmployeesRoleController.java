package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.EmployeesRole;
import ru.zalimannard.bachelorthesisserver.repositories.EmployeesRoleRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.employeesRole}")
public class EmployeesRoleController {
    private final EmployeesRoleRepository employeesRoleRepository;

    public EmployeesRoleController(EmployeesRoleRepository employeesRoleRepository) {
        this.employeesRoleRepository = employeesRoleRepository;
    }

    @GetMapping("{id}")
    public EmployeesRole get(@PathVariable int id) {
        return employeesRoleRepository.retrieve(id);
    }

    @GetMapping
    public List<EmployeesRole> getAll() {
        return employeesRoleRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody EmployeesRole employeesRole) {
        employeesRoleRepository.create(employeesRole);
    }

    @PutMapping
    public void put(@RequestBody EmployeesRole employeesRole) {
        employeesRoleRepository.update(employeesRole);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        employeesRoleRepository.delete(id);
    }
}
