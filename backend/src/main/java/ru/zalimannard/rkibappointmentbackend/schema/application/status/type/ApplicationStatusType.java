package ru.zalimannard.rkibappointmentbackend.schema.application.status.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApplicationStatusType {

    ACTIVE("A"),
    PENDING("P"),
    CANCELED("C");

    @Getter
    private final String code;

}
