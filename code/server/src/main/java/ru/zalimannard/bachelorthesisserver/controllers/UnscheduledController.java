package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Unscheduled;
import ru.zalimannard.bachelorthesisserver.repositories.UnscheduledRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.unscheduled}")
@Tag(name = "Незапланированные обращения")
public class UnscheduledController {
    private final UnscheduledRepository unscheduledRepository;

    public UnscheduledController(UnscheduledRepository unscheduledRepository) {
        this.unscheduledRepository = unscheduledRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение незапланированного обращения")
    public Unscheduled get(@PathVariable int id) {
        return unscheduledRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка незапланированных обращений")
    public List<Unscheduled> getAll() {
        return unscheduledRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового незапланированного обращения")
    public void post(@RequestBody Unscheduled unscheduled) {
        unscheduledRepository.create(unscheduled);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего незапланированного обращения")
    public void put(@RequestBody Unscheduled unscheduled) {
        unscheduledRepository.update(unscheduled);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление незапланированного обращения")
    public void delete(@PathVariable int id) {
        unscheduledRepository.delete(id);
    }
}
