package ru.zalimannard.bachelorthesisserver.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.service}")
@Tag(name = "Услуги")
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение услуги")
    public Service get(@PathVariable int id) {
        return serviceRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка услуг")
    public List<Service> getAll() {
        return serviceRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание новой услуги")
    public void post(@RequestBody Service services) {
        serviceRepository.create(services);
    }

    @PutMapping
    @Operation(summary = "Обновление существующей услуги")
    public void put(@RequestBody Service services) {
        serviceRepository.update(services);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление услуги")
    public void delete(@PathVariable int id) {
        serviceRepository.delete(id);
    }
}
