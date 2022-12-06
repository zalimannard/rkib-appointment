package ru.zalimannard.bachelorthesisserver.appointment.status.type;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitType}")
public class AppointmentStatusTypeController {
    private final AppointmentStatusTypeRepository appointmentStatusTypeRepository;

    public AppointmentStatusTypeController(AppointmentStatusTypeRepository appointmentStatusTypeRepository) {
        this.appointmentStatusTypeRepository = appointmentStatusTypeRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение типа обращений")
    public AppointmentStatusType get(@PathVariable int id) {
        return appointmentStatusTypeRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка типов обращений")
    public List<AppointmentStatusType> getAll() {
        return appointmentStatusTypeRepository.retrieveAll();
    }
}
