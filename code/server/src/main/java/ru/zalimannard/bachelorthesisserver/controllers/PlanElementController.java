package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.PlanElement;
import ru.zalimannard.bachelorthesisserver.repositories.PlanElementRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElement}")
@Tag(name = "Элементы плана")
public class PlanElementController {
    private final PlanElementRepository planElementRepository;

    public PlanElementController(PlanElementRepository planElementRepository) {
        this.planElementRepository = planElementRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента плана")
    public PlanElement get(@PathVariable int id) {
        return planElementRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка элементов плана")
    public List<PlanElement> getAll() {
        return planElementRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента плана")
    public void post(@RequestBody PlanElement planElement) {
        planElementRepository.create(planElement);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего элемента плана")
    public void put(@RequestBody PlanElement planElement) {
        planElementRepository.update(planElement);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента плана")
    public void delete(@PathVariable int id) {
        planElementRepository.delete(id);
    }
}
