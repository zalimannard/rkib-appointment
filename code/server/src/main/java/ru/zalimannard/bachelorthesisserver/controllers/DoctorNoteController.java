package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.DoctorNote;
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
    public DoctorNote get(@PathVariable int id) {
        return doctorNoteRepository.retrieve(id);
    }

    @GetMapping
    public List<DoctorNote> getAll() {
        return doctorNoteRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody DoctorNote doctorNote) {
        doctorNoteRepository.create(doctorNote);
    }

    @PutMapping
    public void put(@RequestBody DoctorNote doctorNote) {
        doctorNoteRepository.update(doctorNote);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        doctorNoteRepository.delete(id);
    }
}
