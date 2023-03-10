package ru.zalimannard.bachelorthesisserver.schema.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}${application.endpoint.application}")
@Tag(name = "Обращения")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationDto get(@PathVariable String id) {
        return applicationService.read(id);
    }

    @GetMapping("${application.endpoint.search}")
    @Operation(summary = "Получение списка обращений")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationDto> search(ApplicationDto exampleApplicationDto,
                                       @RequestParam(value = "pageNo", defaultValue = "${application.constant.defaultPageNumber}", required = false) int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "${application.constant.defaultPageSize}", required = false) int pageSize,
                                       @RequestParam(value = "sort", defaultValue = "${application.constant.defaultSort}", required = false) String[] sort) {
        return applicationService.search(exampleApplicationDto, pageNo, pageSize, sort);
    }

    @PostMapping
    @Operation(summary = "Создание нового обращения")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationDto post(@RequestBody ApplicationDto applicationDto) {
        return applicationService.create(applicationDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление существующего обращения")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationDto put(@PathVariable String id,
                              @RequestBody ApplicationDto applicationDto) {
        return applicationService.update(id, applicationDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationDto delete(@PathVariable String id) {
        return applicationService.delete(id);
    }

}
