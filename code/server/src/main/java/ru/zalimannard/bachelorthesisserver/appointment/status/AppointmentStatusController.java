package ru.zalimannard.bachelorthesisserver.appointment.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitStatus}")
@Tag(name = "Статусы обращений")
public class AppointmentStatusController {
    private final AppointmentStatusRepository appointmentStatusRepository;

    public AppointmentStatusController(AppointmentStatusRepository appointmentStatusRepository) {
        this.appointmentStatusRepository = appointmentStatusRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение статуса обращений")
    public AppointmentStatus get(@PathVariable int id) {
        return appointmentStatusRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка статусов обращений")
    public List<AppointmentStatus> getAll() {
        return appointmentStatusRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового статуса обращений")
    public void post(@RequestBody AppointmentStatus appointmentStatus) {
        appointmentStatusRepository.create(appointmentStatus);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего статуса обращений")
    public void put(@RequestBody AppointmentStatus appointmentStatus) {
        appointmentStatusRepository.update(appointmentStatus);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление статуса обращений")
    public void delete(@PathVariable int id) {
        appointmentStatusRepository.delete(id);
    }
}
