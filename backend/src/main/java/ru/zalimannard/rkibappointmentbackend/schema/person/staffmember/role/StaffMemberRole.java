package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StaffMemberRole {

    ADMIN("A"),
    REGISTRAR("R"),
    DOCTOR("D");

    @Getter
    private final String code;

}