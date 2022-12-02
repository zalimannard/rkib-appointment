package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.InstitutionRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.institution}")
public class InstitutionController {
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return institutionRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return institutionRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        institutionRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        institutionRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        institutionRepository.delete(id);
    }
}
