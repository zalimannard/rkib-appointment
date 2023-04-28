package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberResponseDto;

@Validated
public interface StaffMemberService {

    StaffMemberResponseDto create(@NotNull @Valid StaffMemberRequestDto staffMemberDto);

    StaffMember createEntity(@NotNull StaffMember staffMember);


    StaffMemberResponseDto read(@NotNull String id);

    StaffMember readEntity(@NotNull String id);


    StaffMemberResponseDto update(@NotNull String id, @NotNull @Valid StaffMemberRequestDto staffMemberDto);

    StaffMember updateEntity(@NotNull StaffMember staffMember);


    void delete(@NotNull String id);

}
