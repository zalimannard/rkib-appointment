package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.VisitStatus;
import ru.zalimannard.bachelorthesisserver.repositories.VisitStatusRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitStatus}")
@Tag(name = "Статусы обращений")
public class VisitStatusController {
    private final VisitStatusRepository visitStatusRepository;

    public VisitStatusController(VisitStatusRepository visitStatusRepository) {
        this.visitStatusRepository = visitStatusRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса обращений")
    public VisitStatus get(@PathVariable int id) {
        return visitStatusRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов обращений")
    public List<VisitStatus> getAll() {
        return visitStatusRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса обращений")
    public void post(@RequestBody VisitStatus visitStatus) {
        visitStatusRepository.create(visitStatus);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса обращений")
    public void put(@RequestBody VisitStatus visitStatus) {
        visitStatusRepository.update(visitStatus);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса обращений")
    public void delete(@PathVariable int id) {
        visitStatusRepository.delete(id);
    }
}
