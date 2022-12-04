package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementStatus;
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
    public PlanElementStatus get(@PathVariable int id) {
        return planElementStatusRepository.retrieve(id);
    }

    @GetMapping
    public List<PlanElementStatus> getAll() {
        return planElementStatusRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody PlanElementStatus planElementStatus) {
        planElementStatusRepository.create(planElementStatus);
    }

    @PutMapping
    public void put(@RequestBody PlanElementStatus planElementStatus) {
        planElementStatusRepository.update(planElementStatus);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        planElementStatusRepository.delete(id);
    }
}
