package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Visit;
import ru.zalimannard.bachelorthesisserver.repositories.VisitRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visit}")
@Tag(name = "Обращения")
public class VisitController {
    private final VisitRepository visitRepository;

    public VisitController(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public Visit get(@PathVariable int id) {
        return visitRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка обращений")
    public List<Visit> getAll() {
        return visitRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового обращения")
    public void post(@RequestBody Visit visit) {
        visitRepository.create(visit);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего обращения")
    public void put(@RequestBody Visit visit) {
        visitRepository.update(visit);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    public void delete(@PathVariable int id) {
        visitRepository.delete(id);
    }
}
