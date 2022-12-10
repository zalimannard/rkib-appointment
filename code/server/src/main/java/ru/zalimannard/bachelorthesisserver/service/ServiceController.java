package ru.zalimannard.bachelorthesisserver.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.service}")
@Tag(name = "Услуги")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение услуги")
    public ServiceDto get(@PathVariable int id) {
        return serviceService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка услуг")
    public List<ServiceDto> getAll() {
        return serviceService.list();
    }
}
