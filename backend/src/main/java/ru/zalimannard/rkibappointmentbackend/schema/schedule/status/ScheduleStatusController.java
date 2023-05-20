package ru.zalimannard.rkibappointmentbackend.schema.schedule.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.schedule.status.dto.ScheduleStatusResponseDto;

import java.util.List;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.scheduleStatuses}")
@Tag(name = "Статусы элементов расписания")
@RequiredArgsConstructor
public class ScheduleStatusController {

    private final ScheduleStatusService scheduleStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов расписания")
    public ScheduleStatusResponseDto get(@PathVariable String id) {
        return scheduleStatusService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех статусов элементов расписания")
    public List<ScheduleStatusResponseDto> getAll() {
        return scheduleStatusService.readAll();
    }

    @PostMapping
    @Operation(summary = "Создание статуса элементов расписания")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleStatusResponseDto post(@RequestBody ScheduleStatusRequestDto procedureDto) {
        return scheduleStatusService.create(procedureDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление статуса элементов расписания")
    public ScheduleStatusResponseDto put(@PathVariable String id,
                                         @RequestBody ScheduleStatusRequestDto procedureDto) {
        return scheduleStatusService.update(id, procedureDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов расписания")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        scheduleStatusService.delete(id);
    }

}
