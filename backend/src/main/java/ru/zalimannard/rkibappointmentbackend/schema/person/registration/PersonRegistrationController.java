package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;

@RestController
@RequestMapping("${application.endpoint.registration}")
@Tag(name = "Регистрация пациента")
@RequiredArgsConstructor
public class PersonRegistrationController {

    private final PersonRegistrationService personRegistrationService;

    @PostMapping
    @Operation(summary = "Регистрация нового пациента")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto post(@RequestBody PersonRegistrationDto patientDto) {
        return personRegistrationService.register(patientDto);
    }

}