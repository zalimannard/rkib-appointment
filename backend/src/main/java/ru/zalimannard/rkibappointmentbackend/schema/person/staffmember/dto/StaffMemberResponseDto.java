package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.rkibappointmentbackend.schema.person.dto.PersonResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.role.StaffMemberRole;

import java.util.Set;

@Value
@Builder(toBuilder = true)
public class StaffMemberResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("person")
    PersonResponseDto person;

    @JsonProperty("roles")
    Set<StaffMemberRole> roles;

}
