package ru.zalimannard.rkibappointmentbackend.schema.schedule.status.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ScheduleStatusTypeConverter implements AttributeConverter<ScheduleStatusType, String> {

    @Override
    public String convertToDatabaseColumn(ScheduleStatusType attribute) {
        return attribute.getCode();
    }

    @Override
    public ScheduleStatusType convertToEntityAttribute(String dbData) {
        return Stream.of(ScheduleStatusType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
