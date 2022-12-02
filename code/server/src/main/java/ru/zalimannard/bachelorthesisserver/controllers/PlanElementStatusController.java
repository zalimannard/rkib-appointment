package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.PlanElementStatusRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElementStatus}")
public class PlanElementStatusController {
    private final PlanElementStatusRepository planElementStatusRepository;

    public PlanElementStatusController(PlanElementStatusRepository planElementStatusRepository) {
        this.planElementStatusRepository = planElementStatusRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return planElementStatusRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return planElementStatusRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        planElementStatusRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        planElementStatusRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        planElementStatusRepository.delete(id);
    }
}
