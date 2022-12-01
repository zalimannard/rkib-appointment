package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Patient;
import ru.zalimannard.bachelorthesisserver.repositories.PatientRepository;

import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("{id}")
    public Patient get(@PathVariable int id) {
        return patientRepository.retrieve(id);
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Patient patient) {
        patientRepository.create(patient);
    }

    @PutMapping
    public void put(@RequestBody Patient patient) {
        patientRepository.update(patient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        patientRepository.delete(id);
    }
}
