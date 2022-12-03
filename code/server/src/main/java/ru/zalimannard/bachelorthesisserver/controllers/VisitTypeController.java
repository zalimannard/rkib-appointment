package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zalimannard.bachelorthesisserver.entities.VisitType;
import ru.zalimannard.bachelorthesisserver.repositories.VisitTypeRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.visitType}")
public class VisitTypeController {
    private final VisitTypeRepository visitTypeRepository;

    public VisitTypeController(VisitTypeRepository visitTypeRepository) {
        this.visitTypeRepository = visitTypeRepository;
    }

    @GetMapping("{id}")
    public VisitType get(@PathVariable int id) {
        return visitTypeRepository.retrieve(id);
    }

    @GetMapping
    public List<VisitType> getAll() {
        return visitTypeRepository.retrieveAll();
    }
}
