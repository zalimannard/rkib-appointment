package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.unscheduledVisit}")
@Tag(name = "Незапланированные посещения")
public class UnscheduledVisitController {
    private final UnscheduledVisitService unscheduledVisitService;

    public UnscheduledVisitController(UnscheduledVisitService unscheduledVisitService) {
        this.unscheduledVisitService = unscheduledVisitService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение незапланированного посещения")
    public UnscheduledVisitDto get(@PathVariable int id) {
        return unscheduledVisitService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка незапланированных посещения")
    public List<UnscheduledVisitDto> getAll() {
        return unscheduledVisitService.list();
    }

    @PostMapping
    @Operation(summary = "Создание нового незапланированного посещения")
    public UnscheduledVisitDto post(@RequestBody UnscheduledVisitDto unscheduledVisitDto) {
        return unscheduledVisitService.create(unscheduledVisitDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего незапланированного посещения")
    public UnscheduledVisitDto put(@RequestBody UnscheduledVisitDto unscheduledVisitDto) {
        return unscheduledVisitService.update(unscheduledVisitDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление незапланированного посещения")
    public UnscheduledVisitDto delete(@PathVariable int id) {
        return unscheduledVisitService.delete(id);
    }
}
