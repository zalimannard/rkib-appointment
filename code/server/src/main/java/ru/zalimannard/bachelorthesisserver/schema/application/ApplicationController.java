package ru.zalimannard.bachelorthesisserver.schema.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.application}")
@Tag(name = "Обращения")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public ApplicationDto get(@PathVariable String id) {
        return applicationService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка обращений")
    public List<ApplicationDto> getAll(@QuerydslPredicate ApplicationDto exampleApplicationDto) {
        return applicationService.list(exampleApplicationDto);
    }

    @PostMapping
    @Operation(summary = "Создание нового обращения")
    public ApplicationDto post(@RequestBody ApplicationDto applicationDto) {
        return applicationService.create(applicationDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего обращения")
    public ApplicationDto put(@RequestBody ApplicationDto applicationDto) {
        return applicationService.update(applicationDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    public ApplicationDto delete(@PathVariable String id) {
        return applicationService.delete(id);
    }

}
