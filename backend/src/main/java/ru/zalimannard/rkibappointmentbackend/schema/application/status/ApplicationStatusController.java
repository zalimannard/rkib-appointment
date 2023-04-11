package ru.zalimannard.rkibappointmentbackend.schema.application.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.applicationStatus}")
@Tag(name = "Статусы обращений")
@RequiredArgsConstructor
public class ApplicationStatusController {

    private final ApplicationStatusService applicationStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса обращений")
    public ApplicationStatusDto get(@PathVariable String id) {
        return applicationStatusService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов обращений")
    public List<ApplicationStatusDto> search(ApplicationStatusDto exampleApplicationStatusDto,
                                             @RequestParam(value = "sort",
                                                     defaultValue = "${application.constant.defaultSort}",
                                                     required = false) String[] sort,
                                             @RequestParam(value = "pageSize") int pageSize,
                                             @RequestParam(value = "pageNumber") int pageNumber) {
        return applicationStatusService.search(exampleApplicationStatusDto, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса обращений")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationStatusDto post(@RequestBody ApplicationStatusDto applicationStatusDto) {
        return applicationStatusService.create(applicationStatusDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего статуса обращений")
    public ApplicationStatusDto put(@PathVariable String id,
                                    @RequestBody ApplicationStatusDto applicationStatusDto) {
        return applicationStatusService.update(id, applicationStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса обращений")
    public void delete(@PathVariable String id) {
        applicationStatusService.delete(id);
    }

}