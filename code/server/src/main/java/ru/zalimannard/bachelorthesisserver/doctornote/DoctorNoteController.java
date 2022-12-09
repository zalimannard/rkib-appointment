package ru.zalimannard.bachelorthesisserver.doctornote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.doctorNote}")
@Tag(name = "Направления")
public class DoctorNoteController {
    private final DoctorNoteService doctorNoteService;

    public DoctorNoteController(DoctorNoteService doctorNoteService) {
        this.doctorNoteService = doctorNoteService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение направления")
    @ResponseStatus(HttpStatus.OK)
    public DoctorNoteDto get(@PathVariable int id) {
        return doctorNoteService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка направлений")
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorNoteDto> getAll() {
        return doctorNoteService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового направления")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorNoteDto post(@RequestBody DoctorNoteDto doctorNoteDto) {
        return doctorNoteService.post(doctorNoteDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего направления")
    @ResponseStatus(HttpStatus.OK)
    public DoctorNoteDto put(@RequestBody DoctorNoteDto doctorNoteDto) {
        return doctorNoteService.post(doctorNoteDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление направления")
    @ResponseStatus(HttpStatus.OK)
    public DoctorNoteDto delete(@PathVariable int id) {
        return doctorNoteService.delete(id);
    }
}
