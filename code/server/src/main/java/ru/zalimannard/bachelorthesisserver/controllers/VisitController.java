package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Visit;
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
    public Visit get(@PathVariable int id) {
        return visitRepository.retrieve(id);
    }

    @GetMapping
    public List<Visit> getAll() {
        return visitRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Visit visit) {
        visitRepository.create(visit);
    }

    @PutMapping
    public void put(@RequestBody Visit visit) {
        visitRepository.update(visit);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        visitRepository.delete(id);
    }
}
