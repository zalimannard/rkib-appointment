package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${application.endpoint.persons}")
@Tag(name = "Пользователь")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @Operation(summary = "Добавление нового человека")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto post(@RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить человека по id")
    public PersonDto get(@PathVariable String id) {
        return personService.read(id);
    }

    @PostConstruct
    private void postConstruct() {
        String adminField = "ADMIN";
        PersonDto adminAccountDto = PersonDto.builder()
                .username(System.getenv("ADMIN_USERNAME"))
                .password(System.getenv("ADMIN_PASSWORD"))
                .lastName(adminField)
                .firstName(adminField)
                .patronymic(adminField)
                .gender(PersonGender.MALE)
                .phoneNumber("0000000000")
                .birthdate(Date.from(Instant.now()))
                .address(adminField)
                .occupation(adminField)
                .roles(List.of(PersonRole.ADMIN))
                .build();
        personService.create(adminAccountDto);
    }

}
