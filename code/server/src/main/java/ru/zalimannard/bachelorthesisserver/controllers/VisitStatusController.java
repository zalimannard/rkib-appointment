package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.VisitStatus;
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
    public VisitStatus get(@PathVariable int id) {
        return visitStatusRepository.retrieve(id);
    }

    @GetMapping
    public List<VisitStatus> getAll() {
        return visitStatusRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody VisitStatus visitStatus) {
        visitStatusRepository.create(visitStatus);
    }

    @PutMapping
    public void put(@RequestBody VisitStatus visitStatus) {
        visitStatusRepository.update(visitStatus);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        visitStatusRepository.delete(id);
    }
}
