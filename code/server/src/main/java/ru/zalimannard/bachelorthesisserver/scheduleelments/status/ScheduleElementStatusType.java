package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ScheduleElementStatusType {
    ACTIVE("A"),
    CANCELED("C");

    @Getter
    private final String code;
}
