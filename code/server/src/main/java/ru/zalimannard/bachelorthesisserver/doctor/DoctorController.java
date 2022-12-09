package ru.zalimannard.bachelorthesisserver.doctor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.doctor}")
@Tag(name = "Доктора")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение доктора")
    public DoctorDto get(@PathVariable int id) {
        return doctorService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка докторов")
    public List<DoctorDto> getAll() {
        return doctorService.getAll();
    }
}
