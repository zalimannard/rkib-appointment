package ru.zalimannard.bachelorthesisserver.controllers;

import io.micrometer.common.lang.NonNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.DoctorNote;
import ru.zalimannard.bachelorthesisserver.repositories.DoctorNoteRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.doctorNote}")
@Tag(name = "Направления")
public class DoctorNoteController {
    private final DoctorNoteRepository doctorNoteRepository;

    public DoctorNoteController(DoctorNoteRepository doctorNoteRepository) {
        this.doctorNoteRepository = doctorNoteRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение направления")
    public DoctorNote get(@PathVariable int id) {
        return doctorNoteRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка направлений")
    public List<DoctorNote> getAll() {
        return doctorNoteRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового направления направлений")
    public void post(@RequestBody DoctorNote doctorNote) {
        doctorNoteRepository.create(doctorNote);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего направления")
    public void put(@RequestBody DoctorNote doctorNote) {
        doctorNoteRepository.update(doctorNote);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление направления")
    public void delete(@PathVariable int id) {
        doctorNoteRepository.delete(id);
    }
}
