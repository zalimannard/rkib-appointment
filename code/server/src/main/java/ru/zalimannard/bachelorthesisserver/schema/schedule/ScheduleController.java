package ru.zalimannard.bachelorthesisserver.schema.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.schedule}")
@Tag(name = "Элементы расписания")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента расписания")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDto get(@PathVariable String id) {
        return scheduleService.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Получение списка элементов расписания")
    @ResponseStatus(HttpStatus.OK)
    public List<ScheduleDto> search(ScheduleDto exampleScheduleDto,
                                    @RequestParam(value = "beginTimestamp", required = false) @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE_TIME) Date beginTimestamp,
                                    @RequestParam(value = "endTimestamp", required = false) @DateTimeFormat(iso =
                                            DateTimeFormat.ISO.DATE_TIME) Date endTimestamp,
                                    @RequestParam(value = "pageNo", defaultValue = "${application.constant" +
                                            ".defaultPageNumber}", required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                    @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        System.out.println(beginTimestamp);
        return scheduleService.search(exampleScheduleDto, beginTimestamp, endTimestamp, pageNo, pageSize, sort);
    }

    @PostMapping
    @Operation(summary = "Создание нового элемента расписания")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleDto post(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.create(scheduleDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего элемента расписания")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDto put(@PathVariable String id,
                           @RequestBody ScheduleDto scheduleDto) {
        return scheduleService.update(id, scheduleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента расписания")
    @ResponseStatus(HttpStatus.OK)
    public ScheduleDto delete(@PathVariable String id) {
        return scheduleService.delete(id);
    }

}
