package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.*;
import ru.zalimannard.bachelorthesisserver.entities.AccessRight;
import ru.zalimannard.bachelorthesisserver.repositories.AccessRightRepository;

import java.util.List;

@RestController
@RequestMapping("accessRight")
public class AccessRightController {
    private final AccessRightRepository accessRightRepository;

    public AccessRightController(AccessRightRepository accessRightRepository) {
        this.accessRightRepository = accessRightRepository;
    }

    @GetMapping("{id}")
    public AccessRight get(@PathVariable int id) {
        return accessRightRepository.retrieve(id);
    }

    @GetMapping
    public List<AccessRight> getAll() {
        return accessRightRepository.retrieveAll();
    }

    @PostMapping
    public void post(@RequestBody AccessRight accessRight) {
        accessRightRepository.create(accessRight);
    }

    @PutMapping
    public void put(@RequestBody AccessRight accessRight) {
        accessRightRepository.update(accessRight);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        accessRightRepository.delete(id);
    }
}