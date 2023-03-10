package ru.zalimannard.bachelorthesisserver.schema.doctornote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.doctorNote}")
@Tag(name = "Направления")
@RequiredArgsConstructor
public class DoctorNoteController {

    private final DoctorNoteService doctorNoteService;

    @GetMapping("{id}")
    @Operation(summary = "Получение направления")
    @ResponseStatus(HttpStatus.OK)
    public DoctorNoteDto get(@PathVariable String id) {
        return doctorNoteService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка направлений")
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorNoteDto> getAll(@QuerydslPredicate DoctorNoteDto exampleDoctorNoteDto) {
        return doctorNoteService.list(exampleDoctorNoteDto);
    }

    @PostMapping
    @Operation(summary = "Создание нового направления")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorNoteDto post(@RequestBody DoctorNoteDto doctorNoteDto) {
        return doctorNoteService.create(doctorNoteDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего направления")
    @ResponseStatus(HttpStatus.OK)
    public DoctorNoteDto put(@RequestBody DoctorNoteDto doctorNoteDto) {
        return doctorNoteService.update(doctorNoteDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление направления")
    @ResponseStatus(HttpStatus.OK)
    public DoctorNoteDto delete(@PathVariable String id) {
        return doctorNoteService.delete(id);
    }

}
