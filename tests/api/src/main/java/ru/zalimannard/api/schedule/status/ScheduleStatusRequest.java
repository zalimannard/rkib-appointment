package ru.zalimannard.api.schedule.status;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ScheduleStatusRequest {

    String type;

    String name;

}
