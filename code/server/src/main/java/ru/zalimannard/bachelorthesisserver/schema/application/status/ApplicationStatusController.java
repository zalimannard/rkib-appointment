package ru.zalimannard.bachelorthesisserver.schema.application.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.applicationStatus}")
@Tag(name = "Статусы обращений")
@RequiredArgsConstructor
public class ApplicationStatusController {

    private final ApplicationStatusService applicationStatusService;

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса обращений")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationStatusDto get(@PathVariable String id) {
        return applicationStatusService.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Получение списка статусов обращений")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationStatusDto> search(ApplicationStatusDto exampleApplicationStatusDto,
                                             @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                             @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        return applicationStatusService.search(exampleApplicationStatusDto, pageNo, pageSize, sort);
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса обращений")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationStatusDto post(@RequestBody ApplicationStatusDto applicationStatusDto) {
        return applicationStatusService.create(applicationStatusDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего статуса обращений")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationStatusDto put(@PathVariable String id,
                                    @RequestBody ApplicationStatusDto applicationStatusDto) {
        return applicationStatusService.update(id, applicationStatusDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса обращений")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationStatusDto delete(@PathVariable String id) {
        return applicationStatusService.delete(id);
    }

}
