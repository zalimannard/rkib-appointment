package ru.zalimannard.rkibappointmentbackend.schema.institution;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.institution.dto.InstitutionResponseDto;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.institutions}")
@Tag(name = "Учреждение")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping("{id}")
    @Operation(summary = "Получение учреждения")
    public InstitutionResponseDto get(@PathVariable String id) {
        return institutionService.read(id);
    }

    @PostMapping
    @Operation(summary = "Создание учреждения")
    @ResponseStatus(HttpStatus.CREATED)
    public InstitutionResponseDto post(@RequestBody InstitutionRequestDto procedureDto) {
        return institutionService.create(procedureDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление учреждения")
    public InstitutionResponseDto put(@PathVariable String id,
                                      @RequestBody InstitutionRequestDto procedureDto) {
        return institutionService.update(id, procedureDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление учреждения")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        institutionService.delete(id);
    }

}
