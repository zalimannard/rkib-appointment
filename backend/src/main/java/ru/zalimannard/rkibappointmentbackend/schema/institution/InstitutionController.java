package ru.zalimannard.rkibappointmentbackend.schema.institution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.institution}")
@Tag(name = "Учреждение")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("{id}")
    @Operation(summary = "Получение учреждение")
    public InstitutionDto get(@PathVariable String id) {
        return institutionService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка учреждений")
    public List<InstitutionDto> search(InstitutionDto exampleInstitutionDto,
                                       @RequestParam(value = "sort",
                                               defaultValue = "${application.constant.defaultSort}",
                                               required = false) String[] sort,
                                       @RequestParam(value = "pageSize") int pageSize,
                                       @RequestParam(value = "pageNumber") int pageNumber) {
        return institutionService.search(exampleInstitutionDto, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание учреждение")
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionDto post(@RequestBody InstitutionDto institutionDto) {
        return institutionService.create(institutionDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление учреждения")
    public InstitutionDto put(@PathVariable String id,
                              @RequestBody InstitutionDto institutionDto) {
        return institutionService.update(id, institutionDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление учреждения")
    public void delete(@PathVariable String id) {
        institutionService.delete(id);
    }

}