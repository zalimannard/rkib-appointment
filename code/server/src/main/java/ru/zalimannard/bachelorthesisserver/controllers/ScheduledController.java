package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.ScheduledRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduled}")
public class ScheduledController {
    private final ScheduledRepository scheduledRepository;

    public ScheduledController(ScheduledRepository scheduledRepository) {
        this.scheduledRepository = scheduledRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return scheduledRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return scheduledRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        scheduledRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        scheduledRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        scheduledRepository.delete(id);
    }
}
