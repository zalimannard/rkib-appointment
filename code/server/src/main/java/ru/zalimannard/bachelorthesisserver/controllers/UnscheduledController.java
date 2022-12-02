package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.UnscheduledRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.unscheduled}")
public class UnscheduledController {
    private final UnscheduledRepository unscheduledRepository;

    public UnscheduledController(UnscheduledRepository unscheduledRepository) {
        this.unscheduledRepository = unscheduledRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return unscheduledRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return unscheduledRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        unscheduledRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        unscheduledRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        unscheduledRepository.delete(id);
    }
}
