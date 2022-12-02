package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.PlanElementRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElement}")
public class PlanElementController {
    private final PlanElementRepository planElementRepository;

    public PlanElementController(PlanElementRepository planElementRepository) {
        this.planElementRepository = planElementRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return planElementRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return planElementRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        planElementRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        planElementRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        planElementRepository.delete(id);
    }
}
