package ru.zalimannard.bachelorthesisserver.application.status.type;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitType}")
public class ApplicationStatusTypeController {
    private final ApplicationStatusTypeRepository applicationStatusTypeRepository;

    public ApplicationStatusTypeController(ApplicationStatusTypeRepository applicationStatusTypeRepository) {
        this.applicationStatusTypeRepository = applicationStatusTypeRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение типа обращений")
    public ApplicationStatusType get(@PathVariable int id) {
        return applicationStatusTypeRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка типов обращений")
    public List<ApplicationStatusType> getAll() {
        return applicationStatusTypeRepository.retrieveAll();
    }
}
