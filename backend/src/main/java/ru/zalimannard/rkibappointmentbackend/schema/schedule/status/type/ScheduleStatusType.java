package ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ScheduleStatusType {

    ACTIVE("A"),
    PENDING("P"),
    CANCELED("C");

    @Getter
    private final String code;

}