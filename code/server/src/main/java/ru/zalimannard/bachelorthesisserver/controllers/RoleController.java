package ru.zalimannard.bachelorthesisserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zalimannard.bachelorthesisserver.entities.Role;
import ru.zalimannard.bachelorthesisserver.repositories.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("${application.endpoint.root}" + "${application.endpoint.role}")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("{id}")
    public Role get(@PathVariable int id) {
        return roleRepository.retrieve(id);
    }

    @GetMapping
    public List<Role> getAll() {
        return roleRepository.retrieveAll();
    }
}