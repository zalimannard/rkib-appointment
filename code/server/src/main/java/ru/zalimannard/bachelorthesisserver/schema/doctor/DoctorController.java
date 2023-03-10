package ru.zalimannard.bachelorthesisserver.schema.doctor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.doctor}")
@Tag(name = "Доктора")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("{id}")
    @Operation(summary = "Получение доктора")
    public DoctorDto get(@PathVariable String id) {
        return doctorService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка докторов")
    public List<DoctorDto> getAll(@QuerydslPredicate DoctorDto exampleDoctorDto) {
        return doctorService.list(exampleDoctorDto);
    }
}
