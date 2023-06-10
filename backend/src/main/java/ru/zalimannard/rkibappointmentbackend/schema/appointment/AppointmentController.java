package ru.zalimannard.rkibappointmentbackend.schema.appointment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentResponseDto;

import java.util.List;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.appointments}")
@Tag(name = "Обращение")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public AppointmentResponseDto get(@PathVariable String id) {
        return appointmentService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех обращений")
    public List<AppointmentResponseDto> getAll() {
        return appointmentService.readAll();
    }

    @PostMapping
    @Operation(summary = "Создание обращения")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDto post(@RequestBody AppointmentRequestDto procedureDto) {
        return appointmentService.create(procedureDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление обращения")
    public AppointmentResponseDto put(@PathVariable String id,
                                      @RequestBody AppointmentRequestDto procedureDto) {
        return appointmentService.update(id, procedureDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        appointmentService.delete(id);
    }

}
