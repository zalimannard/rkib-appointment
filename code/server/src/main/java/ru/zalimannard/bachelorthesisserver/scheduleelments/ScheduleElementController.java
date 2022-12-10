package ru.zalimannard.bachelorthesisserver.scheduleelments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElement}")
@Tag(name = "Элементы плана")
public class ScheduleElementController {
    private final ScheduleElementService scheduleElementService;

    public ScheduleElementController(ScheduleElementService scheduleElementService) {
        this.scheduleElementService = scheduleElementService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента плана")
    public ScheduleElementDto get(@PathVariable int id) {
        return scheduleElementService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка элементов плана")
    public List<ScheduleElementDto> getAll() {
        return scheduleElementService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента плана")
    public ScheduleElementDto post(@RequestBody ScheduleElementDto scheduleElementDto) {
        return scheduleElementService.post(scheduleElementDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего элемента плана")
    public ScheduleElementDto put(@RequestBody ScheduleElementDto scheduleElementDto) {
        return scheduleElementService.put(scheduleElementDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента плана")
    public ScheduleElementDto delete(@PathVariable int id) {
        return scheduleElementService.delete(id);
    }
}
