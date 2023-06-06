package ru.zalimannard.api.schedule.status;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ScheduleStatusResponse {

    String id;

    String type;

    String name;

}
