package ru.zalimannard.rkibappointmentbackend.schema.appointment.status.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AppointmentStatusType {

    ACTIVE("A"),
    PENDING("P"),
    CANCELED("C");

    @Getter
    private final String code;

}