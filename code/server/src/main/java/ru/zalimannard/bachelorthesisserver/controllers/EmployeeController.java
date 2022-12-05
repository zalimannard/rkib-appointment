package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.employee}")
@Tag(name = "Работники")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение работника")
    public Employee get(@PathVariable int id) {
        return employeeRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка работников")
    public List<Employee> getAll() {
        return employeeRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового работника")
    public void post(@RequestBody Employee employee) {
        employeeRepository.create(employee);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего работника")
    public void put(@RequestBody Employee employee) {
        employeeRepository.update(employee);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление работника")
    public void delete(@PathVariable int id) {
        employeeRepository.delete(id);
    }
}
