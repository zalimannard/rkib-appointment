package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto.StaffMemberResponseDto;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class StaffMemberMapper {

    private final PersonMapper personMapper;

    public StaffMember toEntity(StaffMemberRequestDto patientRequestDto,
                                Person person) {
        return StaffMember.builder()
                .person(person)
                .roles(new HashSet<>(patientRequestDto.getRoles()))
                .build();
    }

    public StaffMemberResponseDto toDto(StaffMember patient) {
        return StaffMemberResponseDto.builder()
                .id(patient.getId())
                .person(personMapper.toDto(patient.getPerson()))
                .roles(new HashSet<>(patient.getRoles()))
                .build();
    }

}
