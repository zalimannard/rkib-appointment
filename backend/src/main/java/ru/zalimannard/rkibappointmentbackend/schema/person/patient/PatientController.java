package ru.zalimannard.rkibappointmentbackend.schema.person.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.patients}")
@Tag(name = "Пациент")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("{id}")
    @Operation(summary = "Получение пациента")
    public PatientResponseDto get(@PathVariable String id) {
        return patientService.read(id);
    }

    @PostMapping
    @Operation(summary = "Создание пациента")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponseDto post(@RequestBody PatientRequestDto patientDto) {
        return patientService.create(patientDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление пациента")
    public PatientResponseDto put(@PathVariable String id,
                                  @RequestBody PatientRequestDto patientDto) {
        return patientService.update(id, patientDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление пациента")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        patientService.delete(id);
    }

}
