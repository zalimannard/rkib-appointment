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

    @PostMapping
    public void post(@RequestBody AccessRight accessRight) {
        accessRightRepository.create(accessRight);
    }

    @GetMapping
    public List<AccessRight> getAll() {
        return accessRightRepository.readAll();
    }

    @GetMapping("{id}")
    public AccessRight get(@PathVariable Integer id) {
        return accessRightRepository.read(id);
    }

    @PutMapping("{id}")
    public void put(@PathVariable Integer id, @RequestBody AccessRight accessRight) {
        accessRightRepository.update(id, accessRight);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        accessRightRepository.delete(id);
    }
}