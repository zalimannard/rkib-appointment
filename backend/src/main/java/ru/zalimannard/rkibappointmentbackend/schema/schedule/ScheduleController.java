package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${application.endpoint.schedule}")
@Tag(name = "Элемент графика")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента графика")
    public ScheduleDto get(@PathVariable String id) {
        return scheduleService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка элементов графика")
    public List<ScheduleDto> search(ScheduleDto exampleScheduleDto,
                                    @RequestParam(value = "beginTimestamp", required = false) Date beginTimestamp,
                                    @RequestParam(value = "endTimestamp", required = false) Date endTimestamp,
                                    @RequestParam(value = "sort",
                                            defaultValue = "${application.constant.defaultSort}",
                                            required = false) String[] sort,
                                    @RequestParam(value = "pageSize") int pageSize,
                                    @RequestParam(value = "pageNumber") int pageNumber) {
        return scheduleService.search(exampleScheduleDto, beginTimestamp, endTimestamp, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание элемента графика")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleDto post(@RequestBody ScheduleDto scheduleDto) {
        return scheduleService.create(scheduleDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление элемента графика")
    public ScheduleDto put(@PathVariable String id,
                           @RequestBody ScheduleDto scheduleDto) {
        return scheduleService.update(id, scheduleDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента графика")
    public void delete(@PathVariable String id) {
        scheduleService.delete(id);
    }

}