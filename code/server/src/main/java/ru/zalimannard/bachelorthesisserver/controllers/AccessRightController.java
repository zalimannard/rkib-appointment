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

    @GetMapping
    public List<AccessRight> getAll() {
        return accessRightRepository.select();
    }

    @GetMapping("{id}")
    public AccessRight getOne(@PathVariable Integer id) {
        return accessRightRepository.select(id);
    }
}