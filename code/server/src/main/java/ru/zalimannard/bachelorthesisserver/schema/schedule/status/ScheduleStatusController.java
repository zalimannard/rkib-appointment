package ru.zalimannard.bachelorthesisserver.schema.schedule.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.scheduleStatus}")
@Tag(name = "Статусы элементов расписания")
@RequiredArgsConstructor
public class ScheduleStatusController {

    private final ScheduleStatusService scheduleStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов расписания")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleStatusDto get(@PathVariable String id) {
        return scheduleStatusService.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Получение списка статусов элементов расписания")
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleStatusDto> search(ScheduleStatusDto exampleScheduleStatusDto,
                                          @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                          @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        return scheduleStatusService.search(exampleScheduleStatusDto, pageNo, pageSize, sort);
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса элементов расписания")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleStatusDto post(@RequestBody ScheduleStatusDto scheduleStatusDto) {
        return scheduleStatusService.create(scheduleStatusDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего статуса элементов расписания")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleStatusDto put(@PathVariable String id,
                                 @RequestBody ScheduleStatusDto scheduleStatusDto) {
        return scheduleStatusService.update(id, scheduleStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов расписания")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleStatusDto delete(@PathVariable String id) {
        return scheduleStatusService.delete(id);
    }

}
