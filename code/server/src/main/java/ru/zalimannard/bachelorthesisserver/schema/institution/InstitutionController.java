package ru.zalimannard.bachelorthesisserver.schema.institution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.institution}")
@Tag(name = "Учреждения")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("{id}")
    @Operation(summary = "Получение учреждения")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDto get(@PathVariable String id) {
        return institutionService.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Получение списка учреждений")
    @ResponseStatus(HttpStatus.OK)
    public List<InstitutionDto> search(InstitutionDto exampleInstitutionDto,
                                       @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                       @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        return institutionService.search(exampleInstitutionDto, pageNo, pageSize, sort);
    }

    @PostMapping
    @Operation(summary = "Создание нового учреждения")
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionDto post(@RequestBody InstitutionDto institutionDto) {
        return institutionService.create(institutionDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего учреждения")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDto put(@PathVariable String id,
                              @RequestBody InstitutionDto institutionDto) {
        return institutionService.update(id, institutionDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление учреждения")
    @ResponseStatus(HttpStatus.OK)
    public InstitutionDto delete(@PathVariable String id) {
        return institutionService.delete(id);
    }

}
