package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Unscheduled;
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
    public Unscheduled get(@PathVariable int id) {
        return unscheduledRepository.retrieve(id);
    }

    @GetMapping
    public List<Unscheduled> getAll() {
        return unscheduledRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Unscheduled unscheduled) {
        unscheduledRepository.create(unscheduled);
    }

    @PutMapping
    public void put(@RequestBody Unscheduled unscheduled) {
        unscheduledRepository.update(unscheduled);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        unscheduledRepository.delete(id);
    }
}
