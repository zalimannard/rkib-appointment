package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.PlanElementStatus;
import ru.zalimannard.bachelorthesisserver.repositories.PlanElementStatusRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElementStatus}")
@Tag(name = "Статусы элементов плана")
public class PlanElementStatusController {
    private final PlanElementStatusRepository planElementStatusRepository;

    public PlanElementStatusController(PlanElementStatusRepository planElementStatusRepository) {
        this.planElementStatusRepository = planElementStatusRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов плана")
    public PlanElementStatus get(@PathVariable int id) {
        return planElementStatusRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов элементов плана")
    public List<PlanElementStatus> getAll() {
        return planElementStatusRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса элементов плана")
    public void post(@RequestBody PlanElementStatus planElementStatus) {
        planElementStatusRepository.create(planElementStatus);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса элементов плана")
    public void put(@RequestBody PlanElementStatus planElementStatus) {
        planElementStatusRepository.update(planElementStatus);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов плана")
    public void delete(@PathVariable int id) {
        planElementStatusRepository.delete(id);
    }
}
