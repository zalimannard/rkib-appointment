package ru.zalimannard.bachelorthesisserver.appointment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visit}")
@Tag(name = "Обращения")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение обращения")
    public Appointment get(@PathVariable int id) {
        return appointmentRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка обращений")
    public List<Appointment> getAll() {
        return appointmentRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового обращения")
    public void post(@RequestBody Appointment appointment) {
        appointmentRepository.create(appointment);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего обращения")
    public void put(@RequestBody Appointment appointment) {
        appointmentRepository.update(appointment);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление обращения")
    public void delete(@PathVariable int id) {
        appointmentRepository.delete(id);
    }
}
