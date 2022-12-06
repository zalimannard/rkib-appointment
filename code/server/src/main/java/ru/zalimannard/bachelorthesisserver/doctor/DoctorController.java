package ru.zalimannard.bachelorthesisserver.doctor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.employee}")
@Tag(name = "Работники")
public class DoctorController {
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение работника")
    public Doctor get(@PathVariable int id) {
        return doctorRepository.retrieve(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка работников")
    public List<Doctor> getAll() {
        return doctorRepository.retrieveAll();
    }

    @PostMapping
    @Operation(summary = "Создание нового работника")
    public void post(@RequestBody Doctor doctor) {
        doctorRepository.create(doctor);
    }

    @PutMapping
    @Operation(summary = "Обновление существующего работника")
    public void put(@RequestBody Doctor doctor) {
        doctorRepository.update(doctor);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление работника")
    public void delete(@PathVariable int id) {
        doctorRepository.delete(id);
    }
}
