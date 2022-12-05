package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Scheduled;
import ru.zalimannard.bachelorthesisserver.repositories.ScheduledRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduled}")
@Tag(name = "Запланированные обращения")
public class ScheduledController {
    private final ScheduledRepository scheduledRepository;

    public ScheduledController(ScheduledRepository scheduledRepository) {
        this.scheduledRepository = scheduledRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение запланированного обращения")
    public Scheduled get(@PathVariable int id) {
        return scheduledRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка запланированных обращений")
    public List<Scheduled> getAll() {
        return scheduledRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового запланированного обращения направлений")
    public void post(@RequestBody Scheduled scheduled) {
        scheduledRepository.create(scheduled);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего запланированного обращения")
    public void put(@RequestBody Scheduled scheduled) {
        scheduledRepository.update(scheduled);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление запланированного обращения")
    public void delete(@PathVariable int id) {
        scheduledRepository.delete(id);
    }
}
