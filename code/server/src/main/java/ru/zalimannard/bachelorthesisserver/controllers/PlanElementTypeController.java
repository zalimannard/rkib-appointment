package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementType;
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
    public PlanElementType get(@PathVariable int id) {
        return planElementTypeRepository.retrieve(id);
    }

    @GetMapping
    public List<PlanElementType> getAll() {
        return planElementTypeRepository.retrieveAll();
    }
}
