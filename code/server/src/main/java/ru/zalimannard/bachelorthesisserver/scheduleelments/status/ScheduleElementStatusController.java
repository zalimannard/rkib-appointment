package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElementStatus}")
@Tag(name = "Статусы элементов расписания")
@RequiredArgsConstructor
public class ScheduleElementStatusController {
    private final ScheduleElementStatusService scheduleElementStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов расписания")
    public ScheduleElementStatusDto get(@PathVariable String id) {
        return scheduleElementStatusService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов элементов расписания")
    public List<ScheduleElementStatusDto> getAll() {
        return scheduleElementStatusService.list();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса элементов расписания")
    public ScheduleElementStatusDto post(@RequestBody ScheduleElementStatusDto scheduleElementStatusDto) {
        return scheduleElementStatusService.create(scheduleElementStatusDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса элементов расписания")
    public ScheduleElementStatusDto put(@RequestBody ScheduleElementStatusDto scheduleElementStatusDto) {
        return scheduleElementStatusService.update(scheduleElementStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов расписания")
    public ScheduleElementStatusDto delete(@PathVariable String id) {
        return scheduleElementStatusService.delete(id);
    }
}
