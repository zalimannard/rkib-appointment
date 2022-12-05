package ru.zalimannard.bachelorthesisserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Institution;
import ru.zalimannard.bachelorthesisserver.repositories.InstitutionRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.institution}")
@Tag(name = "Учреждения")
public class InstitutionController {
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение учреждения")
    public Institution get(@PathVariable int id) {
        return institutionRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка учреждений")
    public List<Institution> getAll() {
        return institutionRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового учреждения")
    public void post(@RequestBody Institution institution) {
        institutionRepository.create(institution);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего учреждения")
    public void put(@RequestBody Institution institution) {
        institutionRepository.update(institution);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление учреждения")
    public void delete(@PathVariable int id) {
        institutionRepository.delete(id);
    }
}
