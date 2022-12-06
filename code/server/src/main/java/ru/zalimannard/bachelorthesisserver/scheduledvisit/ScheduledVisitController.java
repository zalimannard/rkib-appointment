package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduled}")
@Tag(name = "Запланированные обращения")
public class ScheduledVisitController {
    private final ScheduledVisitRepository scheduledVisitRepository;

    public ScheduledVisitController(ScheduledVisitRepository scheduledVisitRepository) {
        this.scheduledVisitRepository = scheduledVisitRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение запланированного обращения")
    public ScheduledVisit get(@PathVariable int id) {
        return scheduledVisitRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка запланированных обращений")
    public List<ScheduledVisit> getAll() {
        return scheduledVisitRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового запланированного обращения направлений")
    public void post(@RequestBody ScheduledVisit scheduledVisit) {
        scheduledVisitRepository.create(scheduledVisit);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего запланированного обращения")
    public void put(@RequestBody ScheduledVisit scheduledVisit) {
        scheduledVisitRepository.update(scheduledVisit);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление запланированного обращения")
    public void delete(@PathVariable int id) {
        scheduledVisitRepository.delete(id);
    }
}
