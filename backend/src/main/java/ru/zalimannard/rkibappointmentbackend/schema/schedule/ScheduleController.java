package ru.zalimannard.rkibappointmentbackend.schema.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.dto.ScheduleResponseDto;

import java.util.List;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.schedules}")
@Tag(name = "Элемент расписания")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("{id}")
    @Operation(summary = "Получение элемента расписания")
    public ScheduleResponseDto get(@PathVariable String id) {
        return scheduleService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех элементов расписания")
    public List<ScheduleResponseDto> getAll() {
        return scheduleService.readAll();
    }

    @PostMapping
    @Operation(summary = "Создание элемента расписания")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponseDto post(@RequestBody ScheduleRequestDto procedureDto) {
        return scheduleService.create(procedureDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление элемента расписания")
    public ScheduleResponseDto put(@PathVariable String id,
                                   @RequestBody ScheduleRequestDto procedureDto) {
        return scheduleService.update(id, procedureDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление элемента расписания")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        scheduleService.delete(id);
    }

}
