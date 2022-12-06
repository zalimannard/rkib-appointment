package ru.zalimannard.bachelorthesisserver.application.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.applicationStatus}")
@Tag(name = "Статусы обращений")
public class ApplicationStatusController {
    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationStatusController(ApplicationStatusRepository applicationStatusRepository) {
        this.applicationStatusRepository = applicationStatusRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса обращений")
    public ApplicationStatus get(@PathVariable int id) {
        return applicationStatusRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов обращений")
    public List<ApplicationStatus> getAll() {
        return applicationStatusRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса обращений")
    public void post(@RequestBody ApplicationStatus applicationStatus) {
        applicationStatusRepository.create(applicationStatus);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса обращений")
    public void put(@RequestBody ApplicationStatus applicationStatus) {
        applicationStatusRepository.update(applicationStatus);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса обращений")
    public void delete(@PathVariable int id) {
        applicationStatusRepository.delete(id);
    }
}
