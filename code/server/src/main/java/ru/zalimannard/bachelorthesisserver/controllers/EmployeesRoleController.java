package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.EmployeesRole;
import ru.zalimannard.bachelorthesisserver.repositories.EmployeesRoleRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.employeesRole}")
@Tag(name = "Роли работников")
public class EmployeesRoleController {
    private final EmployeesRoleRepository employeesRoleRepository;

    public EmployeesRoleController(EmployeesRoleRepository employeesRoleRepository) {
        this.employeesRoleRepository = employeesRoleRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение роли работника")
    public EmployeesRole get(@PathVariable int id) {
        return employeesRoleRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка ролей работников")
    public List<EmployeesRole> getAll() {
        return employeesRoleRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание новой роли работника")
    public void post(@RequestBody EmployeesRole employeesRole) {
        employeesRoleRepository.create(employeesRole);
    }

    @PutMapping
    @Operation(summary = "Обновление существующей роли работника")
    public void put(@RequestBody EmployeesRole employeesRole) {
        employeesRoleRepository.update(employeesRole);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление роли работника")
    public void delete(@PathVariable int id) {
        employeesRoleRepository.delete(id);
    }
}
