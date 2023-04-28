package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberResponseDto;

@RestController
@RequestMapping("${application.baseApi}${application.apiV1}${application.endpoint.staff}")
@Tag(name = "Сотрудник")
@RequiredArgsConstructor
public class StaffMemberController {

    private final StaffMemberService staffMemberService;

    @GetMapping("{id}")
    @Operation(summary = "Получение сотрудника")
    public StaffMemberResponseDto get(@PathVariable String id) {
        return staffMemberService.read(id);
    }

    @PostMapping
    @Operation(summary = "Создание сотрудника")
    @ResponseStatus(HttpStatus.CREATED)
    public StaffMemberResponseDto post(@RequestBody StaffMemberRequestDto staffMemberDto) {
        return staffMemberService.create(staffMemberDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Обновление сотрудника")
    public StaffMemberResponseDto put(@PathVariable String id,
                                      @RequestBody StaffMemberRequestDto staffMemberDto) {
        return staffMemberService.update(id, staffMemberDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление сотрудника")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        staffMemberService.delete(id);
    }

}
