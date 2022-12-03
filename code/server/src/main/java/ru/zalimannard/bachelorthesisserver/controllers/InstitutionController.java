package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.Institution;
import ru.zalimannard.bachelorthesisserver.repositories.InstitutionRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.institution}")
public class InstitutionController {
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("{id}")
    public Institution get(@PathVariable int id) {
        return institutionRepository.retrieve(id);
    }

    @GetMapping
    public List<Institution> getAll() {
        return institutionRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody Institution institution) {
        institutionRepository.create(institution);
    }

    @PutMapping
    public void put(@RequestBody Institution institution) {
        institutionRepository.update(institution);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        institutionRepository.delete(id);
    }
}
