package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${application.endpoint.persons}")
@Tag(name = "Человек")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("{id}")
    @Operation(summary = "Получение человека")
    public PersonDto get(@PathVariable String id) {
        return personService.read(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка людей")
    public List<PersonDto> search(PersonDto examplePersonDto,
                                  @RequestParam(value = "beginBirthdate", required = false) @DateTimeFormat(pattern
                                          = "yyyy-MM-dd") Date beginBirthdate,
                                  @RequestParam(value = "endBirthdate", required = false) @DateTimeFormat(pattern =
                                          "yyyy-MM-dd") Date endBirthdate,
                                  @RequestParam(value = "sort",
                                          defaultValue = "${application.constant.defaultSort}",
                                          required = false) String[] sort,
                                  @RequestParam(value = "pageSize") int pageSize,
                                  @RequestParam(value = "pageNumber") int pageNumber) {
        return personService.search(examplePersonDto, beginBirthdate, endBirthdate, sort, pageSize, pageNumber);
    }

    @PostMapping
    @Operation(summary = "Создание человека")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto post(@RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление человека")
    public PersonDto put(@PathVariable String id,
                         @RequestBody PersonDto personDto) {
        return personService.update(id, personDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление человека")
    public void delete(@PathVariable String id) {
        personService.delete(id);
    }

    @PostConstruct
    private void postConstruct() {
        if (personService.readEntityByUsername(System.getenv("ADMIN_USERNAME")) == null) {
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

}
