package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Scheduled;
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
    public Scheduled get(@PathVariable int id) {
        return scheduledRepository.retrieve(id);
    }

    @GetMapping
    public List<Scheduled> getAll() {
        return scheduledRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Scheduled scheduled) {
        scheduledRepository.create(scheduled);
    }

    @PutMapping
    public void put(@RequestBody Scheduled scheduled) {
        scheduledRepository.update(scheduled);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        scheduledRepository.delete(id);
    }
}
