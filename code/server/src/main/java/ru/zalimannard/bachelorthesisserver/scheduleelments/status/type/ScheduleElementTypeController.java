package ru.zalimannard.bachelorthesisserver.scheduleelments.status.type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElementStatusType}")
@Tag(name = "Тип элементов плана")
public class ScheduleElementTypeController {
    private final ScheduleElementTypeRepository scheduleElementTypeRepository;

    public ScheduleElementTypeController(ScheduleElementTypeRepository scheduleElementTypeRepository) {
        this.scheduleElementTypeRepository = scheduleElementTypeRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение типа элементов плана")
    public ScheduleElementType get(@PathVariable int id) {
        return scheduleElementTypeRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка типов элементов плана")
    public List<ScheduleElementType> getAll() {
        return scheduleElementTypeRepository.retrieveAll();
    }
}
