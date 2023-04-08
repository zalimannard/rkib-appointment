package ru.zalimannard.rkibappointmentbackend.schema.person.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PersonRole {

    ADMIN("A"),
    REGISTRAR("R"),
    DOCTOR("D"),
    USER("U");

    @Getter
    private final String code;

}