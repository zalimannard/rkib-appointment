package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Employee;
import ru.zalimannard.bachelorthesisserver.repositories.DoctorNoteRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.doctorNote}")
public class DoctorNoteController {
    private final DoctorNoteRepository doctorNoteRepository;

    public DoctorNoteController(DoctorNoteRepository doctorNoteRepository) {
        this.doctorNoteRepository = doctorNoteRepository;
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable int id) {
        return doctorNoteRepository.retrieve(id);
    }

    @GetMapping
    public List<Employee> getAll() {
        return doctorNoteRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Employee employee) {
        doctorNoteRepository.create(employee);
    }

    @PutMapping
    public void put(@RequestBody Employee employee) {
        doctorNoteRepository.update(employee);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        doctorNoteRepository.delete(id);
    }
}
