package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementType;
import ru.zalimannard.bachelorthesisserver.repositories.PlanElementTypeRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElementType}")
@Tag(name = "Тип элементов плана")
public class PlanElementTypeController {
    private final PlanElementTypeRepository planElementTypeRepository;

    public PlanElementTypeController(PlanElementTypeRepository planElementTypeRepository) {
        this.planElementTypeRepository = planElementTypeRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение типа элементов плана")
    public PlanElementType get(@PathVariable int id) {
        return planElementTypeRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка типов элементов плана")
    public List<PlanElementType> getAll() {
        return planElementTypeRepository.retrieveAll();
    }
}
