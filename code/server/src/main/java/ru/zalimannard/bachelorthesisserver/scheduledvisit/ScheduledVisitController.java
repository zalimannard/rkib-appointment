package ru.zalimannard.bachelorthesisserver.scheduledvisit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduledVisit}")
@Tag(name = "Запланированные посещения")
@RequiredArgsConstructor
public class ScheduledVisitController {
    private final ScheduledVisitService scheduledVisitService;

    @GetMapping("{id}")
    @Operation(summary = "Получение запланированного посещения")
    public ScheduledVisitDto get(@PathVariable int id) {
        return scheduledVisitService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка запланированных посещений")
    public List<ScheduledVisitDto> getAll() {
        return scheduledVisitService.list();
    }

    @PostMapping
    @Operation(summary = "Создание нового запланированного посещения")
    public ScheduledVisitDto post(@RequestBody ScheduledVisitDto scheduledVisitDto) {
        return scheduledVisitService.create(scheduledVisitDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего запланированного посещения")
    public ScheduledVisitDto put(@RequestBody ScheduledVisitDto scheduledVisitDto) {
        return scheduledVisitService.update(scheduledVisitDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление запланированного посещения")
    public ScheduledVisitDto delete(@PathVariable int id) {
        return scheduledVisitService.delete(id);
    }
}
