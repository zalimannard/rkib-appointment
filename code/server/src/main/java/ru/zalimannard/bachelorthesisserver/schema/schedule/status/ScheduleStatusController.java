package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElementStatus}")
@Tag(name = "Статусы элементов расписания")
@RequiredArgsConstructor
public class ScheduleStatusController {
    private final ScheduleStatusService scheduleStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов расписания")
    public ScheduleStatusDto get(@PathVariable String id) {
        return scheduleStatusService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов элементов расписания")
    public List<ScheduleStatusDto> getAll(@QuerydslPredicate ScheduleStatusDto exampleScheduleStatusDto) {
        return scheduleStatusService.list(exampleScheduleStatusDto);
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса элементов расписания")
    public ScheduleStatusDto post(@RequestBody ScheduleStatusDto scheduleStatusDto) {
        return scheduleStatusService.create(scheduleStatusDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса элементов расписания")
    public ScheduleStatusDto put(@RequestBody ScheduleStatusDto scheduleStatusDto) {
        return scheduleStatusService.update(scheduleStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов расписания")
    public ScheduleStatusDto delete(@PathVariable String id) {
        return scheduleStatusService.delete(id);
    }
}
