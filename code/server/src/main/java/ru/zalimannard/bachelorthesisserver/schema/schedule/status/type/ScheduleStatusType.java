package ru.zalimannard.bachelorthesisserver.schema.schedule.status.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ScheduleStatusType {
    ACTIVE("A"),
    CANCELED("C");

    @Getter
    private final String code;
}
