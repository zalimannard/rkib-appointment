package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Services;
import ru.zalimannard.bachelorthesisserver.repositories.ServicesRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.services}")
public class ServicesController {
    private final ServicesRepository servicesRepository;

    public ServicesController(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @GetMapping("{id}")
    public Services get(@PathVariable int id) {
        return servicesRepository.retrieve(id);
    }

    @GetMapping
    public List<Services> getAll() {
        return servicesRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Services services) {
        servicesRepository.create(services);
    }

    @PutMapping
    public void put(@RequestBody Services services) {
        servicesRepository.update(services);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        servicesRepository.delete(id);
    }
}
