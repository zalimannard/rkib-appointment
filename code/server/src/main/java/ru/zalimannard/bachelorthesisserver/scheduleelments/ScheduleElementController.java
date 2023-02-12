package ru.zalimannard.bachelorthesisserver.scheduleelments;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElement}")
@Tag(name = "Элементы расписания")
@RequiredArgsConstructor
public class ScheduleElementController {
    private final ScheduleElementService scheduleElementService;

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента расписания")
    public ScheduleElementDto get(@PathVariable String id) {
        return scheduleElementService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка элементов расписания")
    public List<ScheduleElementDto> getAll() {
        return scheduleElementService.list();
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента расписания")
    public ScheduleElementDto post(@RequestBody ScheduleElementDto scheduleElementDto) {
        return scheduleElementService.create(scheduleElementDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего элемента расписания")
    public ScheduleElementDto put(@RequestBody ScheduleElementDto scheduleElementDto) {
        return scheduleElementService.update(scheduleElementDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента расписания")
    public ScheduleElementDto delete(@PathVariable String id) {
        return scheduleElementService.delete(id);
    }
}
