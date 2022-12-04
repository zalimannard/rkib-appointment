package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.PlanElement;
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
    public PlanElement get(@PathVariable int id) {
        return planElementRepository.retrieve(id);
    }

    @GetMapping
    public List<PlanElement> getAll() {
        return planElementRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody PlanElement planElement) {
        planElementRepository.create(planElement);
    }

    @PutMapping
    public void put(@RequestBody PlanElement planElement) {
        planElementRepository.update(planElement);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        planElementRepository.delete(id);
    }
}
