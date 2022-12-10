package ru.zalimannard.bachelorthesisserver.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.application}")
@Tag(name = "Обращения")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public ApplicationDto get(@PathVariable int id) {
        return applicationService.get(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка обращений")
    public List<ApplicationDto> getAll() {
        return applicationService.getAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового обращения")
    public ApplicationDto post(@RequestBody ApplicationDto applicationDto) {
        return applicationService.post(applicationDto);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего обращения")
    public ApplicationDto put(@RequestBody ApplicationDto applicationDto) {
        return applicationService.put(applicationDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    public ApplicationDto delete(@PathVariable int id) {
        return applicationService.delete(id);
    }
}
