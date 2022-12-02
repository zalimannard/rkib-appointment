package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.VisitRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visit}")
public class VisitController {
    private final VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return visitRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return visitRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        visitRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        visitRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        visitRepository.delete(id);
    }
}
