package ru.zalimannard.bachelorthesisserver.schema.doctor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.doctor}")
@Tag(name = "Доктора")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("{id}")
    @Operation(summary = "Получение доктора")
    public DoctorDto get(@PathVariable String id) {
        return doctorService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка докторов")
    public List<DoctorDto> search(@QuerydslPredicate DoctorDto exampleDoctorDto,
                                  @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                  @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        return doctorService.search(exampleDoctorDto, pageNo, pageSize, sort);
    }

}
