package ru.zalimannard.bachelorthesisserver.schema.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElement}")
@Tag(name = "Элементы расписания")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента расписания")
    public ScheduleDto get(@PathVariable String id) {
        return scheduleService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка элементов расписания")
    public List<ScheduleDto> getAll(@QuerydslPredicate ScheduleDto exampleScheduleDto) {
        return scheduleService.list(exampleScheduleDto);
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента расписания")
    public ScheduleDto post(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.create(scheduleDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего элемента расписания")
    public ScheduleDto put(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.update(scheduleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента расписания")
    public ScheduleDto delete(@PathVariable String id) {
        return scheduleService.delete(id);
    }

}
