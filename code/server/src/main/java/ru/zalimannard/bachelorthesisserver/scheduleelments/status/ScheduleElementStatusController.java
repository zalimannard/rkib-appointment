package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElementStatus}")
@Tag(name = "Статусы элементов плана")
public class ScheduleElementStatusController {
    private final ScheduleElementStatusService scheduleElementStatusService;

    public ScheduleElementStatusController(ScheduleElementStatusService scheduleElementStatusService) {
        this.scheduleElementStatusService = scheduleElementStatusService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов плана")
    public ScheduleElementStatusDto get(@PathVariable int id) {
        return scheduleElementStatusService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов элементов плана")
    public List<ScheduleElementStatusDto> getAll() {
        return scheduleElementStatusService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса элементов плана")
    public ScheduleElementStatusDto post(@RequestBody ScheduleElementStatusDto scheduleElementStatusDto) {
        return scheduleElementStatusService.post(scheduleElementStatusDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса элементов плана")
    public ScheduleElementStatusDto put(@RequestBody ScheduleElementStatusDto scheduleElementStatusDto) {
        return scheduleElementStatusService.put(scheduleElementStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов плана")
    public ScheduleElementStatusDto delete(@PathVariable int id) {
        return scheduleElementStatusService.delete(id);
    }
}
