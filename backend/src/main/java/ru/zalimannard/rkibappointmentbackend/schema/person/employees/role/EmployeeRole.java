package ru.zalimannard.rkibappointmentbackend.schema.person.employees.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EmployeeRole {

    ADMIN("A"),
    REGISTRAR("R"),
    DOCTOR("D");

    @Getter
    private final String code;

}