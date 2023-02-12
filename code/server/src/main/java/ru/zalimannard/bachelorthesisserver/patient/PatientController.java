package ru.zalimannard.bachelorthesisserver.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.patient}")
@Tag(name = "Пациенты")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("{id}")
    @Operation(summary = "Получение пациента")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto get(@PathVariable String id) {
        return patientService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка пациентов")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDto> getAll() {
        return patientService.list();
    }

    @PostMapping
    @Operation(summary = "Создание нового пациента")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto post(@RequestBody PatientDto patientDto) {
        return patientService.create(patientDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего пациента")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto put(@RequestBody PatientDto patientDto) {
        return patientService.update(patientDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление пациента")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto delete(@PathVariable String id) {
        return patientService.delete(id);
    }
}
