package ru.zalimannard.rkibappointmentbackend.schema.procedures;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.procedures.dto.ProcedureResponseDto;

import java.util.List;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.procedures}")
@Tag(name = "Процедура")
@RequiredArgsConstructor
public class ProcedureController {

    private final ProcedureService procedureService;

    @GetMapping("{id}")
    @Operation(summary = "Получение процедуры")
    public ProcedureResponseDto get(@PathVariable String id) {
        return procedureService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех процедур")
    public List<ProcedureResponseDto> getAll() {
        return procedureService.readAll();
    }

    @PostMapping
    @Operation(summary = "Создание процедуры")
    @ResponseStatus(HttpStatus.CREATED)
    public ProcedureResponseDto post(@RequestBody ProcedureRequestDto procedureDto) {
        return procedureService.create(procedureDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление процедуры")
    public ProcedureResponseDto put(@PathVariable String id,
                                    @RequestBody ProcedureRequestDto procedureDto) {
        return procedureService.update(id, procedureDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление процедуры")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        procedureService.delete(id);
    }

}
