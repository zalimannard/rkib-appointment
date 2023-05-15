package ru.zalimannard.rkibappointmentbackend.schema.person.employees;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.employees.dto.EmployeeResponseDto;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.employees}")
@Tag(name = "Сотрудник")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("{id}")
    @Operation(summary = "Получение сотрудника")
    public EmployeeResponseDto get(@PathVariable String id) {
        return employeeService.read(id);
    }

    @GetMapping("${application.endpoint.me}")
    @Operation(summary = "Получение сотрудником себя")
    public EmployeeResponseDto getMe() {
        return employeeService.readMe();
    }

    @PostMapping
    @Operation(summary = "Создание сотрудника")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDto post(@RequestBody EmployeeRequestDto staffMemberDto) {
        return employeeService.create(staffMemberDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление сотрудника")
    public EmployeeResponseDto put(@PathVariable String id,
                                   @RequestBody EmployeeRequestDto staffMemberDto) {
        return employeeService.update(id, staffMemberDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление сотрудника")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        employeeService.delete(id);
    }

}
