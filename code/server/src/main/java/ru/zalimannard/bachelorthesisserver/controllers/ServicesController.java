package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
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
    public Employee get(@PathVariable int id) {
        return servicesRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return servicesRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        servicesRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        servicesRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        servicesRepository.delete(id);
    }
}
