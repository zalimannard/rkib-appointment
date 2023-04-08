package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${application.endpoint.persons}")
@Tag(name = "Пользователь")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("{id}")
    @Operation(summary = "Получить по id")
    public PersonDto post(@PathVariable String id) {
        return personService.read(id);
    }

}
