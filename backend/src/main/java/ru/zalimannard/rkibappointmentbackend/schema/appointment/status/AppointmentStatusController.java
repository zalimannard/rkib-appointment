package ru.zalimannard.rkibappointmentbackend.schema.appointment.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusResponseDto;

import java.util.List;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.appointmentStatuses}")
@Tag(name = "Статусы обращений")
@RequiredArgsConstructor
public class AppointmentStatusController {

    private final AppointmentStatusService appointmentStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса обращений")
    public AppointmentStatusResponseDto get(@PathVariable String id) {
        return appointmentStatusService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех статусов обращений")
    public List<AppointmentStatusResponseDto> getAll() {
        return appointmentStatusService.readAll();
    }

    @PostMapping
    @Operation(summary = "Создание статуса обращений")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentStatusResponseDto post(@RequestBody AppointmentStatusRequestDto procedureDto) {
        return appointmentStatusService.create(procedureDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление статуса обращений")
    public AppointmentStatusResponseDto put(@PathVariable String id,
                                            @RequestBody AppointmentStatusRequestDto procedureDto) {
        return appointmentStatusService.update(id, procedureDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса обращений")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        appointmentStatusService.delete(id);
    }

}
