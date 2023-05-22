package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;

import java.util.List;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.people}")
@Tag(name = "Человек")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("{id}")
    @Operation(summary = "Получение человека")
    public PersonResponseDto get(@PathVariable String id) {
        return personService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех людей")
    public List<PersonResponseDto> getAll() {
        return personService.readAll();
    }

    @PostMapping
    @Operation(summary = "Создание человека")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponseDto post(@RequestBody PersonRequestDto personDto) {
        return personService.create(personDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление человека")
    public PersonResponseDto put(@PathVariable String id,
                                 @RequestBody PersonRequestDto personDto) {
        return personService.update(id, personDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление человека")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        personService.delete(id);
    }

}
