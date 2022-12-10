package ru.zalimannard.bachelorthesisserver.scheduleelments.status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ScheduleElementStatusTypeConverter implements AttributeConverter<ScheduleElementStatusType, String> {
    @Override
    public String convertToDatabaseColumn(ScheduleElementStatusType attribute) {
        return attribute.getCode();
    }

    @Override
    public ScheduleElementStatusType convertToEntityAttribute(String dbData) {
        return Stream.of(ScheduleElementStatusType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
