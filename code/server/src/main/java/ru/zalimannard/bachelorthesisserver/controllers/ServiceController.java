package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Service;
import ru.zalimannard.bachelorthesisserver.repositories.ServiceRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.services}")
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("{id}")
    public Service get(@PathVariable int id) {
        return serviceRepository.retrieve(id);
    }

    @GetMapping
    public List<Service> getAll() {
        return serviceRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Service services) {
        serviceRepository.create(services);
    }

    @PutMapping
    public void put(@RequestBody Service services) {
        serviceRepository.update(services);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        serviceRepository.delete(id);
    }
}
