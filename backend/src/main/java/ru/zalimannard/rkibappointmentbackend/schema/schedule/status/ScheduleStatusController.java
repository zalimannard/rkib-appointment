package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.scheduleStatus}")
@Tag(name = "Статусы графиков")
@RequiredArgsConstructor
public class ScheduleStatusController {

    private final ScheduleStatusService scheduleStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса графиков")
    public ScheduleStatusDto get(@PathVariable String id) {
        return scheduleStatusService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов графиков")
    public List<ScheduleStatusDto> search(ScheduleStatusDto exampleScheduleStatusDto,
                                          @RequestParam(value = "sort",
                                                  defaultValue = "${application.constant.defaultSort}",
                                                  required = false) String[] sort,
                                          @RequestParam(value = "pageSize") int pageSize,
                                          @RequestParam(value = "pageNumber") int pageNumber) {
        return scheduleStatusService.search(exampleScheduleStatusDto, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса графиков")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleStatusDto post(@RequestBody ScheduleStatusDto scheduleStatusDto) {
        return scheduleStatusService.create(scheduleStatusDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего статуса графиков")
    public ScheduleStatusDto put(@PathVariable String id,
                                 @RequestBody ScheduleStatusDto scheduleStatusDto) {
        return scheduleStatusService.update(id, scheduleStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса графиков")
    public void delete(@PathVariable String id) {
        scheduleStatusService.delete(id);
    }

}