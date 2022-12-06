package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.scheduleElementStatus}")
@Tag(name = "Статусы элементов плана")
public class ScheduleElementStatusController {
    private final ScheduleElementStatusRepository planElementStatusRepository;

    public ScheduleElementStatusController(ScheduleElementStatusRepository planElementStatusRepository) {
        this.planElementStatusRepository = planElementStatusRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса элементов плана")
    public ScheduleElementStatus get(@PathVariable int id) {
        return planElementStatusRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов элементов плана")
    public List<ScheduleElementStatus> getAll() {
        return planElementStatusRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса элементов плана")
    public void post(@RequestBody ScheduleElementStatus scheduleElementStatus) {
        planElementStatusRepository.create(scheduleElementStatus);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса элементов плана")
    public void put(@RequestBody ScheduleElementStatus scheduleElementStatus) {
        planElementStatusRepository.update(scheduleElementStatus);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса элементов плана")
    public void delete(@PathVariable int id) {
        planElementStatusRepository.delete(id);
    }
}
