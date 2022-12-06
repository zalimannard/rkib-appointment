package ru.zalimannard.bachelorthesisserver.scheduleelments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.planElement}")
@Tag(name = "Элементы плана")
public class ScheduleElementController {
    private final ScheduleElementRepository planElementRepository;

    public ScheduleElementController(ScheduleElementRepository planElementRepository) {
        this.planElementRepository = planElementRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента плана")
    public ScheduleElement get(@PathVariable int id) {
        return planElementRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка элементов плана")
    public List<ScheduleElement> getAll() {
        return planElementRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента плана")
    public void post(@RequestBody ScheduleElement scheduleElement) {
        planElementRepository.create(scheduleElement);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего элемента плана")
    public void put(@RequestBody ScheduleElement scheduleElement) {
        planElementRepository.update(scheduleElement);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента плана")
    public void delete(@PathVariable int id) {
        planElementRepository.delete(id);
    }
}
