package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.unscheduledVisit}")
@Tag(name = "Незапланированные обращения")
public class UnscheduledVisitController {
    private final UnscheduledVisitRepository unscheduledVisitRepository;

    public UnscheduledVisitController(UnscheduledVisitRepository unscheduledVisitRepository) {
        this.unscheduledVisitRepository = unscheduledVisitRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение незапланированного обращения")
    public UnscheduledVisit get(@PathVariable int id) {
        return unscheduledVisitRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка незапланированных обращений")
    public List<UnscheduledVisit> getAll() {
        return unscheduledVisitRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового незапланированного обращения")
    public void post(@RequestBody UnscheduledVisit unscheduledVisit) {
        unscheduledVisitRepository.create(unscheduledVisit);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего незапланированного обращения")
    public void put(@RequestBody UnscheduledVisit unscheduledVisit) {
        unscheduledVisitRepository.update(unscheduledVisit);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление незапланированного обращения")
    public void delete(@PathVariable int id) {
        unscheduledVisitRepository.delete(id);
    }
}
