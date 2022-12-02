package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.VisitTypeRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitType}")
public class VisitTypeController {
    private final VisitTypeRepository visitTypeRepository;

    public VisitTypeController(VisitTypeRepository visitTypeRepository) {
        this.visitTypeRepository = visitTypeRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return visitTypeRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return visitTypeRepository.retrieveAll();
    }
}
