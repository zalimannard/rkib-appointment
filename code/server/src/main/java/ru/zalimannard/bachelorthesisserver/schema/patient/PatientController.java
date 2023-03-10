package ru.zalimannard.bachelorthesisserver.schema.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.patient}")
@Tag(name = "Пациенты")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("{id}")
    @Operation(summary = "Получение пациента")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto get(@PathVariable String id) {
        return patientService.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Получение списка пациентов")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDto> search(PatientDto examplePatientDto,
                                   @RequestParam(value = "beginBirthdate", required = false) @DateTimeFormat(pattern
                                           = "yyyy-MM-dd") Date beginBirthdate,
                                   @RequestParam(value = "endBirthdate", required = false) @DateTimeFormat(pattern =
                                           "yyyy-MM-dd") Date endBirthdate,
                                   @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                   @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                   @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        System.out.println(beginBirthdate);
        return patientService.search(examplePatientDto, beginBirthdate, endBirthdate, pageNo, pageSize, sort);
    }

    @PostMapping
    @Operation(summary = "Создание нового пациента")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto post(@RequestBody PatientDto patientDto) {
        return patientService.create(patientDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего пациента")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto put(@PathVariable String id,
                          @RequestBody PatientDto patientDto) {
        return patientService.update(id, patientDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление пациента")
    @ResponseStatus(HttpStatus.OK)
    public PatientDto delete(@PathVariable String id) {
        return patientService.delete(id);
    }

}
