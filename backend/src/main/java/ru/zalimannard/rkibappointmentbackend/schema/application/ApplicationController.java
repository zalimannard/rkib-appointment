package ru.zalimannard.rkibappointmentbackend.schema.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.application}")
@Tag(name = "Обращение")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public ApplicationDto get(@PathVariable String id) {
        return applicationService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка обращений")
    public List<ApplicationDto> search(ApplicationDto exampleApplicationDto,
                                       @RequestParam(value = "sort",
                                               defaultValue = "${application.constant.defaultSort}",
                                               required = false) String[] sort,
                                       @RequestParam(value = "pageSize") int pageSize,
                                       @RequestParam(value = "pageNumber") int pageNumber) {
        return applicationService.search(exampleApplicationDto, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание обращения")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationDto post(@RequestBody ApplicationDto applicationDto) {
        return applicationService.create(applicationDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление обращения")
    public ApplicationDto put(@PathVariable String id,
                              @RequestBody ApplicationDto applicationDto) {
        return applicationService.update(id, applicationDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    public void delete(@PathVariable String id) {
        applicationService.delete(id);
    }

}