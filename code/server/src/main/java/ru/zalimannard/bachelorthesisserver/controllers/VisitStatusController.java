package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.VisitStatusRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitStatus}")
public class VisitStatusController {
    private final VisitStatusRepository visitStatusRepository;

    public VisitStatusController(VisitStatusRepository visitStatusRepository) {
        this.visitStatusRepository = visitStatusRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return visitStatusRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return visitStatusRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        visitStatusRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        visitStatusRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        visitStatusRepository.delete(id);
    }
}
