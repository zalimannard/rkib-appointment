package ru.zalimannard.bachelorthesisserver.institution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public InstitutionDto get(@PathVariable int id,
                              @RequestParam(required = false) Object expand) {
        return institutionService.get(id, expand != null);
    }

    @GetMapping
    @Operation(summary = "Получение списка учреждений")
    public List<InstitutionDto> getAll(@RequestParam(required = false) Object expand) {
        return institutionService.getAll(expand != null);
    }

    @PostMapping
    @Operation(summary = "Создание нового учреждения")
    public InstitutionDto post(@RequestBody InstitutionEntity institutionEntity) {
        return institutionService.post(institutionEntity);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего учреждения")
    public InstitutionDto put(@RequestBody InstitutionEntity institutionEntity) {
        return institutionService.put(institutionEntity);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление учреждения")
    public InstitutionDto delete(@PathVariable int id) {
        return institutionService.delete(id);
    }
}
