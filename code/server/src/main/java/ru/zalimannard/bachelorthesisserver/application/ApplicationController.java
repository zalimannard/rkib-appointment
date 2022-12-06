package ru.zalimannard.bachelorthesisserver.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visit}")
@Tag(name = "Обращения")
public class ApplicationController {
    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public Application get(@PathVariable int id) {
        return applicationRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка обращений")
    public List<Application> getAll() {
        return applicationRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового обращения")
    public void post(@RequestBody Application application) {
        applicationRepository.create(application);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего обращения")
    public void put(@RequestBody Application application) {
        applicationRepository.update(application);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    public void delete(@PathVariable int id) {
        applicationRepository.delete(id);
    }
}
