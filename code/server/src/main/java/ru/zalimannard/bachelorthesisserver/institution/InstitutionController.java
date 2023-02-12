package ru.zalimannard.bachelorthesisserver.institution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.institution}")
@Tag(name = "Учреждения")
public class InstitutionController {
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение учреждения")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDto get(@PathVariable int id) {
        return institutionService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка учреждений")
    @ResponseStatus(HttpStatus.OK)
    public List<InstitutionDto> getAll() {
        return institutionService.list();
    }

    @PostMapping
    @Operation(summary = "Создание нового учреждения")
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionDto post(@RequestBody InstitutionDto institutionDto) {
        return institutionService.create(institutionDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего учреждения")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDto put(@RequestBody InstitutionDto institutionDto) {
        return institutionService.update(institutionDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление учреждения")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDto delete(@PathVariable int id) {
        return institutionService.delete(id);
    }
}
