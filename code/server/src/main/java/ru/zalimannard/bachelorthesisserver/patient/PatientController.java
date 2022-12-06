package ru.zalimannard.bachelorthesisserver.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.patient}")
@Tag(name = "Пациенты")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение пациента")
    public Patient get(@PathVariable int id) {
        return patientRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка пациентов")
    public List<Patient> getAll() {
        return patientRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового пациента")
    public void post(@RequestBody Patient patient) {
        patientRepository.create(patient);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего пациента")
    public void put(@RequestBody Patient patient) {
        patientRepository.update(patient);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление пациента")
    public void delete(@PathVariable int id) {
        patientRepository.delete(id);
    }
}
