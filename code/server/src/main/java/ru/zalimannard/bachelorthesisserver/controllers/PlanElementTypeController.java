package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.PlanElementTypeRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElementType}")
public class PlanElementTypeController {
    private final PlanElementTypeRepository planElementTypeRepository;

    public PlanElementTypeController(PlanElementTypeRepository planElementTypeRepository) {
        this.planElementTypeRepository = planElementTypeRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return planElementTypeRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return planElementTypeRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        planElementTypeRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        planElementTypeRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        planElementTypeRepository.delete(id);
    }
}
